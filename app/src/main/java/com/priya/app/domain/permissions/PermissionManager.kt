package com.priya.app.domain.permissions

import kotlinx.coroutines.flow.StateFlow

enum class PermissionType {
    MICROPHONE,
    CONTACTS,
    PHONE,
    SMS,
    LOCATION,
    NOTIFICATIONS,
    CALENDAR,
}

data class PermissionRequest(
    val type: PermissionType,
    val rationale: String,
    val requiredFor: String,
)

interface PermissionManager {
    val permissionState: StateFlow<Map<PermissionType, PermissionStatus>>
    suspend fun hasPermission(type: PermissionType): Boolean
    suspend fun requestPermission(type: PermissionType): Boolean
    suspend fun explainWhy(type: PermissionType): String
    suspend fun shouldRequestAgain(type: PermissionType): Boolean
}

enum class PermissionStatus {
    GRANTED,
    DENIED,
    BLOCKED,
    UNKNOWN,
}
