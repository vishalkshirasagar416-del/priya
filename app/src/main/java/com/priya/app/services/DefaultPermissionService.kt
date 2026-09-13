package com.priya.app.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.priya.app.domain.permissions.PermissionManager
import com.priya.app.domain.permissions.PermissionType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultPermissionService @Inject constructor(
    private val context: Context,
    private val permissionManager: PermissionManager,
) : PermissionService {

    override suspend fun hasMicrophonePermission(): Boolean {
        val granted = context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        Log.i("PriyaVoice", "MIC_PERMISSION_CHECK granted=$granted")
        return granted
    }

    override suspend fun requestMicrophonePermission(): Boolean {
        val granted = hasMicrophonePermission()
        if (granted) {
            Log.i("PriyaVoice", "MIC_PERMISSION_OK")
            return true
        }
        Log.w("PriyaVoice", "MIC_PERMISSION_NOT_GRANTED; request must be initiated by the Activity UI")
        return false
    }
}
