package com.priya.app.data.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.priya.app.data.local.PriyaDatabase
import com.priya.app.domain.scheduler.ScheduledTaskStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getStringExtra("task_id") ?: return

        val pendingResult = goAsync()
        val appContext = context.applicationContext

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val database = PriyaDatabase.getInstance(appContext)
                val task = database.priyaDao().getScheduledTask(taskId)
                    ?: return@launch

                val domain = task.toDomain()

                database.priyaDao().updateScheduledTaskStatus(
                    taskId,
                    ScheduledTaskStatus.DISPATCHED.name,
                )

                NotificationHelper.show(appContext, domain)
            } finally {
                pendingResult.finish()
            }
        }
    }
}
