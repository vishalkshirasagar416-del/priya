package com.priya.app.data.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.priya.app.data.local.PriyaDao
import com.priya.app.data.local.ScheduledTaskEntity
import com.priya.app.domain.scheduler.ScheduledTask
import com.priya.app.domain.scheduler.ScheduledTaskPolicy
import com.priya.app.domain.scheduler.ScheduledTaskScheduler
import com.priya.app.domain.scheduler.ScheduledTaskStatus
import com.priya.app.domain.scheduler.TaskType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriyaSchedulerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val priyaDao: PriyaDao,
) : ScheduledTaskScheduler {

    private val alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override suspend fun schedule(task: ScheduledTask): Result<ScheduledTask> = withContext(Dispatchers.IO) {
        if (task.scheduledAtEpochMillis <= System.currentTimeMillis()) {
            return@withContext Result.failure(IllegalArgumentException("Task must be scheduled in the future."))
        }

        val duplicates = priyaDao.getPendingScheduledTasks().map { it.toDomain() }
        if (duplicates.any { ScheduledTaskPolicy.isDuplicate(it, task) }) {
            return@withContext Result.failure(IllegalStateException("A duplicate pending task already exists."))
        }

        val persisted = task.copy(status = ScheduledTaskStatus.PENDING)
        priyaDao.upsertScheduledTask(ScheduledTaskEntity.fromDomain(persisted))

        val scheduled = if (scheduleAndroid(persisted)) {
            persisted
        } else {
            priyaDao.updateScheduledTaskStatus(persisted.id, ScheduledTaskStatus.CANCELLED.name)
            return@withContext Result.failure(IllegalStateException("Android could not create this scheduled task."))
        }

        Result.success(scheduled)
    }

    override suspend fun cancel(taskId: String): Result<Unit> = withContext(Dispatchers.IO) {
        cancelAlarm(taskId)
        cancelWork(taskId)
        priyaDao.updateScheduledTaskStatus(taskId, ScheduledTaskStatus.CANCELLED.name)
        Result.success(Unit)
    }

    override suspend fun cancelMatching(type: TaskType, title: String): Result<Unit> = withContext(Dispatchers.IO) {
        val pending = priyaDao.getPendingScheduledTasks().map { it.toDomain() }
        val matches = pending.filter { it.type == type && it.title.equals(title, ignoreCase = true) }
        matches.forEach { cancel(it.id) }
        Result.success(Unit)
    }

    override suspend fun listPending(): List<ScheduledTask> = withContext(Dispatchers.IO) {
        priyaDao.getPendingScheduledTasks().map { it.toDomain() }
    }

    override suspend fun restoreAfterReboot(): Result<Unit> = withContext(Dispatchers.IO) {
        val pending = priyaDao.getPendingScheduledTasks().map { it.toDomain() }
        pending.forEach { task ->
            if (task.scheduledAtEpochMillis > System.currentTimeMillis()) {
                scheduleAndroid(task)
            } else {
                priyaDao.updateScheduledTaskStatus(task.id, ScheduledTaskStatus.MISSED.name)
            }
        }
        Result.success(Unit)
    }

    private fun scheduleAndroid(task: ScheduledTask): Boolean {
        return when (task.type) {
            TaskType.ALARM, TaskType.TIMER -> scheduleExactAlarm(task)
            TaskType.REMINDER, TaskType.NOTIFICATION -> scheduleDeferredWork(task)
        }
    }

    private fun scheduleDeferredWork(task: ScheduledTask): Boolean {
        if (!NotificationHelper.hasPermission(context)) return false
        val workManager = WorkManager.getInstance(context)
        val delayMs = maxOf(task.scheduledAtEpochMillis - System.currentTimeMillis(), 0L)
        val request = OneTimeWorkRequestBuilder<ScheduledTaskWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .build(),
            )
            .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("task_id" to task.id))
            .addTag(task.id)
            .build()

        workManager.enqueueUniqueWork(task.id, ExistingWorkPolicy.REPLACE, request)
        return true
    }

    private fun scheduleExactAlarm(task: ScheduledTask): Boolean {
        if (!NotificationHelper.hasPermission(context)) return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            val requested = BatteryOptimizationHelper.requestIgnoreBatteryOptimization(context)
            if (!requested) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    data = android.net.Uri.fromParts("package", context.packageName, null)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            }
            return false
        }

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = "com.priya.app.ALARM_TRIGGER"
            putExtra("task_id", task.id)
            putExtra("task_type", task.type.name)
        }
        val requestCode = task.id.hashCode()
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                task.scheduledAtEpochMillis,
                pendingIntent,
            )
            return true
        } catch (exception: SecurityException) {
            return false
        }
    }

    private fun cancelAlarm(taskId: String) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = "com.priya.app.ALARM_TRIGGER"
            putExtra("task_id", taskId)
        }
        val pi = PendingIntent.getBroadcast(
            context,
            taskId.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        alarmManager.cancel(pi)
    }

    private fun cancelWork(taskId: String) {
        WorkManager.getInstance(context).cancelUniqueWork(taskId)
    }
}
