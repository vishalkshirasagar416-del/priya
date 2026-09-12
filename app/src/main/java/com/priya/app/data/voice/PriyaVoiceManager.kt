package com.priya.app.data.voice

import android.util.Log
import com.priya.app.core.AudioState
import com.priya.app.domain.voice.TextToSpeechEngine
import com.priya.app.domain.voice.VoiceContext
import com.priya.app.domain.voice.VoiceEnginePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriyaVoiceManager @Inject constructor(
    private val elevenLabsTTSProvider: ElevenLabsTTSProvider,
    private val kokoroTTSProvider: KokoroTTSProvider,
    private val localAndroidTTSProvider: LocalAndroidTTSProvider,
    private val voicePreferences: VoicePreferences,
    private val kokoroModelManager: KokoroModelManager,
) {
    private val _voiceState = MutableStateFlow(AudioState.IDLE)
    val voiceState: StateFlow<AudioState> = _voiceState.asStateFlow()

    suspend fun speak(text: String, context: VoiceContext = VoiceContext.GENERAL): Result<Unit> {
        if (shouldInterruptOn(text)) {
            return stop()
        }

        val settings = voicePreferences.getSettings()
        val engineOrder = getPriorityOrder(settings.selectedVoiceEngine)
        val failures = mutableListOf<Pair<String, Throwable>>()

        _voiceState.value = AudioState.SPEAKING
        Log.i("PriyaTTS", "[TTS] Engine selected: ${settings.selectedVoiceEngine.name}")

        for (engine in engineOrder) {
            val result = runCatching { engine.speak(text, context) }
            if (result.isSuccess) {
                _voiceState.value = AudioState.IDLE
                Log.i("PriyaTTS", "[TTS] Audio playback completed with ${engine.name}")
                return Result.success(Unit)
            }
            val failure = result.exceptionOrNull() ?: IllegalStateException("Unknown TTS failure")
            failures += engine.name to failure
            Log.w("PriyaTTS", "[TTS] ${engine.name} failed: ${failure.message}; trying fallback")
        }

        _voiceState.value = AudioState.ERROR
        return Result.failure(failures.lastOrNull()?.second ?: IllegalStateException("All voice engines failed."))
    }

    suspend fun stop(): Result<Unit> {
        val result = listOf(
            elevenLabsTTSProvider.stop(),
            kokoroTTSProvider.stop(),
            localAndroidTTSProvider.stop(),
        ).lastOrNull { it.isFailure } ?: Result.success(Unit)
        _voiceState.value = AudioState.IDLE
        return result
    }

    fun shouldInterruptOn(text: String): Boolean {
        val normalized = text.trim().lowercase()
        return normalized == "stop" ||
            normalized == "cancel" ||
            normalized == "enough" ||
            normalized.contains("stop") ||
            normalized.contains("cancel") ||
            normalized.contains("enough")
    }

    fun getPriorityOrder(preference: VoiceEnginePreference): List<TextToSpeechEngine> {
        val all = listOf(
            elevenLabsTTSProvider,
            kokoroTTSProvider,
            localAndroidTTSProvider,
        )

        return when (preference) {
            VoiceEnginePreference.AUTO -> listOf(elevenLabsTTSProvider, kokoroTTSProvider, localAndroidTTSProvider)
            VoiceEnginePreference.ELEVENLABS -> listOf(elevenLabsTTSProvider, kokoroTTSProvider, localAndroidTTSProvider)
            VoiceEnginePreference.KOKORO -> listOf(kokoroTTSProvider, localAndroidTTSProvider, elevenLabsTTSProvider)
            VoiceEnginePreference.ANDROID_LOCAL -> listOf(localAndroidTTSProvider, elevenLabsTTSProvider, kokoroTTSProvider)
        }.filter { engine -> engine.isConfigured || engine.name == "AndroidLocalTTS" }
    }

    fun getKokoroModelState() = kokoroModelManager.state
    fun hasKokoroModel() = kokoroModelManager.hasModel()
    suspend fun ensureKokoroModel() = kokoroModelManager.ensureModel()
}
