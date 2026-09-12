package com.priya.app.services

interface PermissionService {
    suspend fun hasMicrophonePermission(): Boolean
    suspend fun requestMicrophonePermission(): Boolean
}
