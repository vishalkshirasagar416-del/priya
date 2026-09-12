package com.priya.app.data.scheduler

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.priya.app.data.local.PriyaDatabase
import com.priya.app.domain.scheduler.ScheduledTaskStatus

class ScheduledTaskWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val taskId = inputData.getString("task_id") ?: return Result.failure()
        val database = PriyaDatabase.getInstance(applicationContext)
        val taskEntity = database.priyaDao().getScheduledTask(taskId) ?: return Result.failure()
        val task = taskEntity.toDomain()
        database.priyaDao().updateScheduledTaskStatus(taskId, ScheduledTaskStatus.DISPATCHED.name)
        NotificationHelper.show(applicationContext, task)
        return Result.success()
    }
}
