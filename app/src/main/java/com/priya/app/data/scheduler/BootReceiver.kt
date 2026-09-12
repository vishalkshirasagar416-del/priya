package com.priya.app.data.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.priya.app.data.local.PriyaDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED &&
            intent.action != Intent.ACTION_LOCKED_BOOT_COMPLETED &&
            intent.action != "android.intent.action.QUICKBOOT_POWERON"
        ) {
            return
        }

        val database = PriyaDatabase.getInstance(context.applicationContext)
        val scheduler = PriyaSchedulerImpl(context.applicationContext, database.priyaDao())

        CoroutineScope(Dispatchers.IO).launch {
            scheduler.restoreAfterReboot()
        }
    }
}
