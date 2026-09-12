package com.priya.app.data.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.priya.app.core.AudioState
import com.priya.app.domain.voice.TextToSpeechEngine
import com.priya.app.domain.voice.VoiceContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalAndroidTTSProvider @Inject constructor(
    private val context: Context,
) : TextToSpeechEngine {
    private var tts: TextToSpeech? = null
    private val _state = MutableStateFlow(AudioState.IDLE)
    override val state: StateFlow<AudioState> = _state.asStateFlow()
    override val isConfigured: Boolean = true
    override val name: String = "AndroidLocalTTS"

    override suspend fun speak(text: String, context: VoiceContext): Result<Unit> {
        return try {
            _state.value = AudioState.SPEAKING
            if (tts == null) {
                tts = TextToSpeech(this@LocalAndroidTTSProvider.context.applicationContext) { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        val locale = Locale("en", "IN")
                        tts?.language = locale
                        tts?.setPitch(1.1f)
                        tts?.setSpeechRate(1.0f)
                        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "priya-voice")
                    }
                }
            } else {
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "priya-voice")
            }
            Result.success(Unit)
        } catch (throwable: Throwable) {
            _state.value = AudioState.ERROR
            Log.w("PriyaAI", "Local Android TTS failed: ${throwable.message}")
            Result.failure(throwable)
        }
    }

    override suspend fun stop(): Result<Unit> {
        tts?.stop()
        _state.value = AudioState.IDLE
        return Result.success(Unit)
    }

    override suspend fun shutdown(): Result<Unit> {
        tts?.stop()
        tts?.shutdown()
        tts = null
        _state.value = AudioState.IDLE
        return Result.success(Unit)
    }

    override fun supportsLanguage(languageCode: String): Boolean {
        return languageCode.contains("en") || languageCode.contains("hi") || languageCode.contains("kn")
    }

    override fun shouldInterruptOn(text: String): Boolean {
        val normalized = text.trim().lowercase()
        return normalized in setOf("stop", "cancel", "enough") ||
            normalized.contains("stop") ||
            normalized.contains("cancel") ||
            normalized.contains("enough")
    }
}
