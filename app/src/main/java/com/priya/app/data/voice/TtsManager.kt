package com.priya.app.data.voice

import android.util.Log
import com.priya.app.domain.voice.TextToSpeechEngine
import com.priya.app.domain.voice.VoiceContext
import com.priya.app.domain.voice.VoiceEnginePreference

class TtsManager(
    private val enginePreference: VoiceEnginePreference = VoiceEnginePreference.AUTO,
    private val engines: List<TextToSpeechEngine>,
) {

    constructor(
        enginePreference: VoiceEnginePreference = VoiceEnginePreference.AUTO,
        elevenLabs: TextToSpeechEngine,
        kokoro: TextToSpeechEngine,
        local: TextToSpeechEngine,
    ) : this(
        enginePreference = enginePreference,
        engines = listOf(elevenLabs, kokoro, local),
    )

    fun getPriorityOrder(): List<String> = when (enginePreference) {
        VoiceEnginePreference.AUTO -> listOf("ElevenLabs", "Kokoro", "AndroidLocalTTS")
        VoiceEnginePreference.ELEVENLABS -> listOf("ElevenLabs", "Kokoro", "AndroidLocalTTS")
        VoiceEnginePreference.KOKORO -> listOf("Kokoro", "AndroidLocalTTS", "ElevenLabs")
        VoiceEnginePreference.ANDROID_LOCAL -> listOf("AndroidLocalTTS", "ElevenLabs", "Kokoro")
    }

    fun selectEngineForText(text: String): TextToSpeechEngine {
        val ordered = when (enginePreference) {
            VoiceEnginePreference.AUTO -> listOf(
                engines.firstOrNull { it.name == "ElevenLabs" },
                engines.firstOrNull { it.name == "Kokoro" },
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
            )
            VoiceEnginePreference.ELEVENLABS -> listOf(
                engines.firstOrNull { it.name == "ElevenLabs" },
                engines.firstOrNull { it.name == "Kokoro" },
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
            )
            VoiceEnginePreference.KOKORO -> listOf(
                engines.firstOrNull { it.name == "Kokoro" },
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
                engines.firstOrNull { it.name == "ElevenLabs" },
            )
            VoiceEnginePreference.ANDROID_LOCAL -> listOf(
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
                engines.firstOrNull { it.name == "ElevenLabs" },
                engines.firstOrNull { it.name == "Kokoro" },
            )
        }.filterNotNull()

        val fallback = ordered.firstOrNull { it.isConfigured || it.name == "AndroidLocalTTS" }
            ?: engines.firstOrNull() ?: error("No TTS engine is registered")

        Log.i("PriyaTTS", "[TTS] Selected engine: ${fallback.name} for preference=${enginePreference.name}")
        return fallback
    }

    suspend fun speak(text: String, context: VoiceContext = VoiceContext.GENERAL): Result<Unit> {
        val ordered = when (enginePreference) {
            VoiceEnginePreference.AUTO -> listOf(
                engines.firstOrNull { it.name == "ElevenLabs" },
                engines.firstOrNull { it.name == "Kokoro" },
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
            )
            VoiceEnginePreference.ELEVENLABS -> listOf(
                engines.firstOrNull { it.name == "ElevenLabs" },
                engines.firstOrNull { it.name == "Kokoro" },
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
            )
            VoiceEnginePreference.KOKORO -> listOf(
                engines.firstOrNull { it.name == "Kokoro" },
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
                engines.firstOrNull { it.name == "ElevenLabs" },
            )
            VoiceEnginePreference.ANDROID_LOCAL -> listOf(
                engines.firstOrNull { it.name == "AndroidLocalTTS" },
                engines.firstOrNull { it.name == "ElevenLabs" },
                engines.firstOrNull { it.name == "Kokoro" },
            )
        }.filterNotNull()

        for (engine in ordered) {
            val result = runCatching { engine.speak(text, context) }
            if (result.isSuccess) return Result.success(Unit)
            Log.w("PriyaTTS", "[TTS] ${engine.name} failed, trying fallback: ${result.exceptionOrNull()?.message}")
        }

        val fallback = engines.firstOrNull() ?: return Result.failure(IllegalStateException("No TTS engine available"))
        return runCatching { fallback.speak(text, context) }
    }
}
