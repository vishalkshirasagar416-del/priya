package com.priya.app.data.tools

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
import com.priya.app.data.scheduler.NotificationHelper
import com.priya.app.domain.scheduler.ScheduledTask
import com.priya.app.domain.scheduler.ScheduledTaskScheduler
import com.priya.app.domain.scheduler.TaskType
import com.priya.app.domain.tools.AssistantTool
import com.priya.app.domain.tools.ToolParameter
import com.priya.app.domain.tools.ToolResult
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

abstract class BaseTool(
    override val name: String,
    override val description: String,
    override val parameters: List<ToolParameter>,
    private val context: Context,
) : AssistantTool {
    protected fun openIntent(intent: Intent): ToolResult {
        return try {
            if (intent.resolveActivity(context.packageManager) == null) {
                ToolResult(false, null, "Application not installed or intent cannot be resolved")
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                ToolResult(true, "Opened successfully")
            }
        } catch (e: ActivityNotFoundException) {
            ToolResult(false, null, "Application not installed or activity unavailable")
        } catch (e: SecurityException) {
            ToolResult(false, null, "Permission denied: ${e.message}")
        }
    }
}

@Singleton
class OpenAppTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_app",
    description = "Open an installed Android app by package name.",
    parameters = listOf(
        ToolParameter("package_name", "string", true, "Android package name to open")
    ),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val packageName = arguments["package_name"] ?: return ToolResult(false, null, "Package name is required")
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            ?: return ToolResult(false, null, "Application not installed")
        return openIntent(intent)
    }
}

@Singleton
class OpenSettingsTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_settings",
    description = "Open Android settings.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        return openIntent(Intent(Settings.ACTION_SETTINGS).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
    }
}

@Singleton
class OpenDialerTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_dialer",
    description = "Open the dialer keypad.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"))
        return openIntent(intent)
    }
}

@Singleton
class MakeCallTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "make_call",
    description = "Place a call to a phone number or contact name after confirmation.",
    parameters = listOf(
        ToolParameter("phone_number", "string", false, "Phone number to call"),
        ToolParameter("contact_name", "string", false, "Contact name to resolve")
    ),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val phoneNumber = arguments["phone_number"]
        val contactName = arguments["contact_name"]
        return when {
            !phoneNumber.isNullOrBlank() -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                openIntent(intent)
            }
            !contactName.isNullOrBlank() -> {
                val matches = resolveContactNumbers(contactName)
                if (matches.isEmpty()) return ToolResult(false, null, "No contact found with that name")
                if (matches.size > 1) return ToolResult(false, "I found multiple contacts with that name. Which one do you mean?", null, mapOf("matches" to matches))
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${matches.first()}"))
                openIntent(intent)
            }
            else -> ToolResult(false, null, "Phone number or contact name is required")
        }
    }

    private fun resolveContactNumbers(name: String): List<String> {
        val results = mutableListOf<String>()
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val selection = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE ?"
        val args = arrayOf("%$name%")
        val cursor = context.contentResolver.query(uri, projection, selection, args, null)
        cursor?.use {
            val numberIndex = it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            while (it.moveToNext()) {
                val number = it.getString(numberIndex)
                if (!number.isNullOrBlank()) results.add(number)
            }
        }
        return results
    }
}

@Singleton
class OpenWhatsAppTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_whatsapp",
    description = "Open WhatsApp.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/"))
        return openIntent(intent)
    }
}

@Singleton
class OpenBrowserTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_browser",
    description = "Open a web browser at the provided URL.",
    parameters = listOf(ToolParameter("url", "string", true, "URL to open")),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val url = arguments["url"] ?: return ToolResult(false, null, "URL is required")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        return openIntent(intent)
    }
}

@Singleton
class SearchWebTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "search_web",
    description = "Open a search query in the browser.",
    parameters = listOf(ToolParameter("query", "string", true, "Search query")),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val query = arguments["query"] ?: return ToolResult(false, null, "Query is required")
        val encoded = Uri.encode(query)
        return openIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$encoded")))
    }
}

@Singleton
class GetTimeTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_time",
    description = "Get the current time.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val now = java.time.LocalTime.now()
        return ToolResult(true, "Current time: ${now.withNano(0)}")
    }
}

@Singleton
class GetDateTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_date",
    description = "Get the current date.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val now = java.time.LocalDate.now()
        return ToolResult(true, "Current date: $now")
    }
}

@Singleton
class GetBatteryStatusTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_battery_status",
    description = "Get remaining battery information.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryIntent?.getIntExtra("level", -1) ?: -1
        val scale = batteryIntent?.getIntExtra("scale", -1) ?: -1
        val percentage = if (level >= 0 && scale > 0) ((level.toFloat() / scale.toFloat()) * 100).toInt() else -1
        return ToolResult(true, "Battery status: ${if (percentage >= 0) "$percentage%" else "unknown"}")
    }
}

@Singleton
class GetStorageStatusTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_storage_status",
    description = "Fetch available storage info.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val stat = android.os.StatFs(context.filesDir.absolutePath)
        val availableBytes = stat.availableBytes
        val totalBytes = stat.totalBytes
        val availableMb = availableBytes / (1024 * 1024)
        val totalMb = totalBytes / (1024 * 1024)
        return ToolResult(true, "Storage available: ${availableMb}MB of ${totalMb}MB")
    }
}

@Singleton
class GetNetworkStatusTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_network_status",
    description = "Get current network information.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? android.net.ConnectivityManager
        val active = connectivity?.activeNetworkInfo
        val state = active?.state?.name ?: "unknown"
        val type = active?.typeName ?: "unknown"
        return ToolResult(true, "Network: $type / $state")
    }
}

@Singleton
class GetDeviceInfoTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_device_info",
    description = "Get device-level summary information.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val version = Build.VERSION.RELEASE
        val sdk = Build.VERSION.SDK_INT
        return ToolResult(true, "Device: $manufacturer $model, Android $version (SDK $sdk)")
    }
}

@Singleton
class SetAlarmTool @Inject constructor(
    private val context: Context,
    private val scheduler: ScheduledTaskScheduler,
) : BaseTool(
    name = "set_alarm",
    description = "Set an alarm for the given time.",
    parameters = listOf(
        ToolParameter("hour", "integer", true, "Alarm hour in 24-hour format"),
        ToolParameter("minute", "integer", true, "Alarm minute"),
        ToolParameter("label", "string", false, "Alarm label")
    ),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val hour = arguments["hour"]?.toIntOrNull() ?: return ToolResult(false, null, "Valid hour is required")
        val minute = arguments["minute"]?.toIntOrNull() ?: return ToolResult(false, null, "Valid minute is required")
        val label = arguments["label"] ?: "Priya alarm"
        val scheduledAt = nextOccurrence(hour, minute)
        val task = ScheduledTask(
            type = TaskType.ALARM,
            title = label,
            description = "Alarm scheduled for ${String.format("%02d:%02d", hour, minute)}",
            scheduledAtEpochMillis = scheduledAt,
            isExact = true,
        )
        val result = scheduler.schedule(task)
        return if (result.isSuccess) {
            ToolResult(true, "Alarm scheduled for ${String.format("%02d:%02d", hour, minute)}.")
        } else {
            ToolResult(false, null, result.exceptionOrNull()?.message ?: "Alarm could not be created on this device.")
        }
    }

    private fun nextOccurrence(hour: Int, minute: Int): Long {
        val now = java.time.LocalDateTime.now()
        val candidate = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0)
        val adjusted = if (candidate.isBefore(now)) candidate.plusDays(1) else candidate
        return adjusted.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}

@Singleton
class CancelAlarmTool @Inject constructor(
    private val context: Context,
    private val scheduler: ScheduledTaskScheduler,
) : BaseTool(
    name = "cancel_alarm",
    description = "Cancel a currently active alarm.",
    parameters = listOf(
        ToolParameter("hour", "integer", false, "Alarm hour in 24-hour format"),
        ToolParameter("minute", "integer", false, "Alarm minute"),
        ToolParameter("label", "string", false, "Alarm label"),
    ),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val label = arguments["label"] ?: "Priya alarm"
        val result = scheduler.cancelMatching(TaskType.ALARM, label)
        return if (result.isSuccess) {
            ToolResult(true, "Alarm cancelled.")
        } else {
            ToolResult(false, null, result.exceptionOrNull()?.message ?: "No alarm was found to cancel.")
        }
    }
}

@Singleton
class CreateTimerTool @Inject constructor(
    private val context: Context,
    private val scheduler: ScheduledTaskScheduler,
) : BaseTool(
    name = "create_timer",
    description = "Create a timer with the desired length in seconds.",
    parameters = listOf(ToolParameter("seconds", "integer", true, "Timer length in seconds")),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val seconds = arguments["seconds"]?.toLongOrNull() ?: return ToolResult(false, null, "Seconds is required")
        if (seconds <= 0L) return ToolResult(false, null, "Timer length must be greater than zero.")
        val task = ScheduledTask(
            type = TaskType.TIMER,
            title = "Priya timer",
            description = "Timer for ${seconds}s",
            scheduledAtEpochMillis = System.currentTimeMillis() + (seconds * 1000L),
            isExact = true,
        )
        val result = scheduler.schedule(task)
        return if (result.isSuccess) {
            ToolResult(true, "Timer scheduled for ${seconds} seconds.")
        } else {
            ToolResult(false, null, result.exceptionOrNull()?.message ?: "Timer could not be scheduled.")
        }
    }
}

@Singleton
class CreateReminderTool @Inject constructor(
    private val context: Context,
    private val scheduler: ScheduledTaskScheduler,
) : BaseTool(
    name = "create_reminder",
    description = "Create a reminder for a date and time.",
    parameters = listOf(
        ToolParameter("title", "string", true, "Reminder title"),
        ToolParameter("date", "string", true, "Reminder date in YYYY-MM-DD format"),
        ToolParameter("time", "string", true, "Reminder time in HH:mm format"),
        ToolParameter("description", "string", false, "Optional reminder description")
    ),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val title = arguments["title"] ?: return ToolResult(false, null, "Title is required")
        val date = arguments["date"] ?: return ToolResult(false, null, "Date is required")
        val time = arguments["time"] ?: return ToolResult(false, null, "Time is required")
        val description = arguments["description"] ?: "" 

        if (!NotificationHelper.hasPermission(context)) {
            return ToolResult(false, null, "Notification permission is required before Priya can trigger reminders.")
        }
        val scheduledAt = try {
            LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        } catch (_: Exception) {
            return ToolResult(false, null, "Reminder date or time format is invalid. Use YYYY-MM-DD and HH:mm.")
        }
        val task = ScheduledTask(
            type = TaskType.REMINDER,
            title = title,
            description = description,
            scheduledAtEpochMillis = scheduledAt,
            isExact = false,
        )
        val result = scheduler.schedule(task)
        return if (result.isSuccess) {
            ToolResult(true, "Reminder scheduled for $date at $time.")
        } else {
            ToolResult(false, null, result.exceptionOrNull()?.message ?: "Reminder could not be scheduled.")
        }
    }
}

@Singleton
class DeleteReminderTool @Inject constructor(
    private val context: Context,
    private val scheduler: ScheduledTaskScheduler,
) : BaseTool(
    name = "delete_reminder",
    description = "Delete a reminder entry by title.",
    parameters = listOf(ToolParameter("title", "string", true, "Reminder title to delete")),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val title = arguments["title"] ?: return ToolResult(false, null, "Reminder title is required")
        val result = scheduler.cancelMatching(TaskType.REMINDER, title)
        return if (result.isSuccess) {
            ToolResult(true, "Reminder cancelled.")
        } else {
            ToolResult(false, null, result.exceptionOrNull()?.message ?: "No reminder was found to cancel.")
        }
    }
}

@Singleton
class GetLocationTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "get_location",
    description = "Read the current location if available.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        return ToolResult(false, null, "Location access requires permission and a configured location provider.")
    }
}

@Singleton
class OpenMapsTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_maps",
    description = "Open Maps to a location or coordinates.",
    parameters = listOf(
        ToolParameter("query", "string", false, "Place or query for maps"),
        ToolParameter("latitude", "string", false, "Latitude"),
        ToolParameter("longitude", "string", false, "Longitude")
    ),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val query = arguments["query"]
        val latitude = arguments["latitude"]
        val longitude = arguments["longitude"]
        return when {
            !query.isNullOrBlank() -> openIntent(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${Uri.encode(query)}")))
            !latitude.isNullOrBlank() && !longitude.isNullOrBlank() -> openIntent(Intent(Intent.ACTION_VIEW, Uri.parse("geo:${latitude},${longitude}")))
            else -> ToolResult(false, null, "Map query or coordinates are required")
        }
    }
}

@Singleton
class OpenCalendarTool @Inject constructor(
    private val context: Context,
) : BaseTool(
    name = "open_calendar",
    description = "Open the calendar app.",
    parameters = emptyList(),
    context = context,
) {
    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("content://com.android.calendar/time")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return openIntent(intent)
    }
}
