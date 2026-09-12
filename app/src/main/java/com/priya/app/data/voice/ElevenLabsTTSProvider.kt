package com.priya.app.data.voice

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import com.priya.app.BuildConfig
import com.priya.app.core.AudioState
import com.priya.app.domain.voice.TextToSpeechEngine
import com.priya.app.domain.voice.VoiceContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ElevenLabsTTSProvider @Inject constructor(
    private val context: Context,
) : TextToSpeechEngine {
    private val _state = MutableStateFlow(AudioState.IDLE)
    override val state: StateFlow<AudioState> = _state.asStateFlow()
    override val isConfigured: Boolean = BuildConfig.ELEVENLABS_API_KEY.isNotBlank()
    override val name: String = "ElevenLabs"

    private var tts: TextToSpeech? = null
    private var mediaPlayer: MediaPlayer? = null

    override suspend fun speak(text: String, context: VoiceContext): Result<Unit> {
        if (!isConfigured) {
            return Result.failure(IllegalStateException("ElevenLabs API key is not configured."))
        }

        return try {
            _state.value = AudioState.SPEAKING
            val audioUrl = createVoiceRequest(text)
            val streamUrl = audioUrl ?: return Result.failure(IllegalStateException("Failed to generate speech."))
            playAudioUrl(streamUrl)
            Result.success(Unit)
        } catch (throwable: Throwable) {
            _state.value = AudioState.ERROR
            Log.w("PriyaAI", "ElevenLabs TTS failed: ${sanitize(throwable.message)}")
            Result.failure(throwable)
        }
    }

    override suspend fun stop(): Result<Unit> {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        _state.value = AudioState.IDLE
        return Result.success(Unit)
    }

    override suspend fun shutdown(): Result<Unit> {
        mediaPlayer?.release()
        mediaPlayer = null
        _state.value = AudioState.IDLE
        return Result.success(Unit)
    }

    override fun supportsLanguage(languageCode: String): Boolean = languageCode.startsWith("en") || languageCode.startsWith("hi") || languageCode.startsWith("kn")

    override fun shouldInterruptOn(text: String): Boolean {
        val normalized = text.trim().lowercase()
        return normalized in setOf("stop", "cancel", "enough") ||
            normalized.contains("stop") ||
            normalized.contains("cancel") ||
            normalized.contains("enough")
    }

    private fun createVoiceRequest(text: String): String? {
        val request = JSONObject().apply {
            put("text", text)
            put("model_id", "eleven_multilingual_v2")
            put("voice_settings", JSONObject().apply {
                put("stability", 0.5)
                put("similarity_boost", 0.8)
                put("style", 0.2)
                put("use_speaker_boost", true)
            })
        }

        val connection = URL("https://api.elevenlabs.io/v1/text-to-speech/21m00Tcm4TlvDq8ikWAM").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "audio/mpeg")
        connection.setRequestProperty("xi-api-key", BuildConfig.ELEVENLABS_API_KEY)
        connection.doOutput = true
        connection.connectTimeout = 20000
        connection.readTimeout = 30000

        OutputStreamWriter(connection.outputStream).use { writer ->
            writer.write(request.toString())
            writer.flush()
        }

        if (connection.responseCode !in 200..299) {
            val error = connection.errorStream?.bufferedReader()?.readText().orEmpty()
            Log.w("PriyaAI", "ElevenLabs request failed: ${sanitize(error)}")
            return null
        }

        val tempFile = java.io.File.createTempFile("priya-tts", ".mp3")
        connection.inputStream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return tempFile.absolutePath
    }

    private fun playAudioUrl(path: String) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(path)
            prepare()
            start()
            setOnCompletionListener {
                _state.value = AudioState.IDLE
            }
        }
    }

    private fun sanitize(value: String?): String {
        if (value.isNullOrBlank()) return "unknown error"
        return value.replace(Regex("(?i)(xi-api-key:|xi-api-key\\s*=\\s*)([^\\s,]+)"), "xi-api-key=[REDACTED]")
            .replace(Regex("(?i)(Bearer )[A-Za-z0-9._-]+"), "[REDACTED]")
    }
}
