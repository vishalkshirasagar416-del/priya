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
        val database = PriyaDatabase.getInstance(context.applicationContext)
        val task = database.priyaDao().getScheduledTask(taskId) ?: return
        val domain = task.toDomain()
        database.priyaDao().updateScheduledTaskStatus(taskId, ScheduledTaskStatus.DISPATCHED.name)
        NotificationHelper.show(context.applicationContext, domain)

        CoroutineScope(Dispatchers.IO).launch {
            database.priyaDao().updateScheduledTaskStatus(taskId, ScheduledTaskStatus.DISPATCHED.name)
        }
    }
}
