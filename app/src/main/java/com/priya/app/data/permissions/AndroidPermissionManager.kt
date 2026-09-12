package com.priya.app.data.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.priya.app.domain.permissions.PermissionManager
import com.priya.app.domain.permissions.PermissionRequest
import com.priya.app.domain.permissions.PermissionStatus
import com.priya.app.domain.permissions.PermissionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidPermissionManager @Inject constructor(
    private val context: Context,
) : PermissionManager {
    private val permissionStateFlow = MutableStateFlow(
        mapOf(
            PermissionType.MICROPHONE to PermissionStatus.UNKNOWN,
            PermissionType.CONTACTS to PermissionStatus.UNKNOWN,
            PermissionType.PHONE to PermissionStatus.UNKNOWN,
            PermissionType.SMS to PermissionStatus.UNKNOWN,
            PermissionType.LOCATION to PermissionStatus.UNKNOWN,
            PermissionType.NOTIFICATIONS to PermissionStatus.UNKNOWN,
            PermissionType.CALENDAR to PermissionStatus.UNKNOWN,
        )
    )

    override val permissionState: StateFlow<Map<PermissionType, PermissionStatus>> = permissionStateFlow.asStateFlow()

    override suspend fun hasPermission(type: PermissionType): Boolean {
        val status = toStatus(type)
        permissionStateFlow.value = permissionStateFlow.value + (type to status)
        return status == PermissionStatus.GRANTED
    }

    override suspend fun requestPermission(type: PermissionType): Boolean {
        if (hasPermission(type)) return true
        val rationale = explainWhy(type)
        val requested = when (type) {
            PermissionType.MICROPHONE -> context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
            PermissionType.CONTACTS -> context.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
            PermissionType.PHONE -> context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ||
                context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
            PermissionType.SMS -> context.checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
            PermissionType.LOCATION -> context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            PermissionType.NOTIFICATIONS -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
            PermissionType.CALENDAR -> context.checkSelfPermission(Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED
        }

        permissionStateFlow.value = permissionStateFlow.value + (type to if (requested) PermissionStatus.GRANTED else PermissionStatus.DENIED)
        return requested
    }

    override suspend fun explainWhy(type: PermissionType): String = when (type) {
        PermissionType.MICROPHONE -> "This permission is required so Priya can listen to your voice and understand commands."
        PermissionType.CONTACTS -> "This permission is required to look up a contact name before making a call or opening a chat."
        PermissionType.PHONE -> "This permission is required to place a call or access phone features safely."
        PermissionType.SMS -> "This permission is required to open messaging or send SMS actions when you approve them."
        PermissionType.LOCATION -> "This permission is required to find your location or open maps for directions."
        PermissionType.NOTIFICATIONS -> "This permission is required to send reminders and alerts that you can see."
        PermissionType.CALENDAR -> "This permission is required to create or manage calendar reminders."
    }

    override suspend fun shouldRequestAgain(type: PermissionType): Boolean {
        return permissionStateFlow.value[type] != PermissionStatus.BLOCKED
    }

    private fun toStatus(type: PermissionType): PermissionStatus {
        val granted = when (type) {
            PermissionType.MICROPHONE -> context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
            PermissionType.CONTACTS -> context.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
            PermissionType.PHONE -> context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ||
                context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
            PermissionType.SMS -> context.checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
            PermissionType.LOCATION -> context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            PermissionType.NOTIFICATIONS -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
            PermissionType.CALENDAR -> context.checkSelfPermission(Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED
        }

        return if (granted) PermissionStatus.GRANTED else PermissionStatus.DENIED
    }
}
