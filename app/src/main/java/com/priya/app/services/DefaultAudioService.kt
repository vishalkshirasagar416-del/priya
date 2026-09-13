package com.priya.app.services

import android.Manifest
import android.content.Context
import android.util.Log
import com.priya.app.data.voice.AndroidSpeechToTextProvider
import com.priya.app.data.voice.PriyaVoiceManager
import com.priya.app.domain.permissions.PermissionManager
import com.priya.app.domain.permissions.PermissionType
import com.priya.app.domain.voice.SpeechToText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAudioService @Inject constructor(
    private val context: Context,
    private val speechToText: SpeechToText,
    private val voiceManager: PriyaVoiceManager,
    private val permissionManager: PermissionManager,
) : AudioService {

    override suspend fun startListening(): Result<Unit> = withContext(Dispatchers.Main.immediate) {
        return@withContext try {
            if (!permissionManager.hasPermission(PermissionType.MICROPHONE)) {
                Log.w("PriyaVoice", "MIC_PERMISSION_MISSING")
                return@withContext Result.failure(SecurityException("Microphone permission is required."))
            }
            Log.i("PriyaVoice", "VOICE_START")
            val result = speechToText.startListening()
            if (result.isSuccess) {
                Log.i("PriyaVoice", "LISTENING_STARTED")
            } else {
                Log.e("PriyaVoice", "STT_ERROR ${result.exceptionOrNull()?.message}")
            }
            result
        } catch (throwable: Throwable) {
            Log.e("PriyaVoice", "STT_ERROR ${throwable.message}", throwable)
            Result.failure(throwable)
        }
    }

    override suspend fun stopListening(): Result<Unit> = withContext(Dispatchers.Main.immediate) {
        return@withContext runCatching {
            speechToText.stopListening()
            Log.i("PriyaVoice", "LISTENING_STOPPED")
        }
    }

    override suspend fun speakText(text: String): Result<Unit> = withContext(Dispatchers.Main.immediate) {
        return@withContext try {
            Log.i("PriyaTTS", "TTS_START textLength=${text.length}")
            val result = voiceManager.speak(text)
            if (result.isSuccess) {
                Log.i("PriyaTTS", "TTS_COMPLETE")
            } else {
                Log.e("PriyaTTS", "TTS_ERROR ${result.exceptionOrNull()?.message}")
            }
            result
        } catch (throwable: Throwable) {
            Log.e("PriyaTTS", "TTS_ERROR ${throwable.message}", throwable)
            Result.failure(throwable)
        }
    }
}
