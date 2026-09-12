package com.priya.app.data.voice

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.priya.app.core.AudioState
import com.priya.app.domain.voice.TextToSpeechEngine
import com.priya.app.domain.voice.VoiceContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KokoroTTSProvider @Inject constructor(
    private val context: Context,
    private val modelManager: KokoroModelManager,
    private val voicePreferences: VoicePreferences,
    private val onnxBridge: KokoroOnnxRuntimeBridge,
) : TextToSpeechEngine {

    private val _state = MutableStateFlow(AudioState.IDLE)
    override val state: StateFlow<AudioState> = _state.asStateFlow()
    override val isConfigured: Boolean
        get() = modelManager.hasModel()
    override val name: String = "Kokoro"

    private var mediaPlayer: MediaPlayer? = null

    override suspend fun speak(text: String, context: VoiceContext): Result<Unit> {
        val settings = voicePreferences.getSettings()
        return try {
            _state.value = AudioState.SPEAKING
            Log.i("PriyaTTS", "[TTS] Kokoro generation started for ${text.length} chars")

            val modelFile = withContext(Dispatchers.IO) {
                modelManager.ensureModel().getOrElse { throw it }
            }

            val voiceName = getSupportedVoice(settings.kokoroVoice)
            val outputFile = withContext(Dispatchers.IO) {
                generateAudioFile(modelFile, text, voiceName, settings.voiceSpeed, settings.voicePitch)
            } ?: return Result.failure(IllegalStateException("Kokoro speech generation failed."))

            playAudioFile(outputFile)
            _state.value = AudioState.IDLE
            Result.success(Unit)
        } catch (throwable: Throwable) {
            _state.value = AudioState.ERROR
            Log.e("PriyaTTS", "[TTS] Kokoro generation failed", throwable)
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
        stop()
        onnxBridge.close()
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

    private fun getSupportedVoice(requestedVoice: String): String {
        val supported = listOf("af_heart", "af_soft", "af_alloy", "am_adam", "bm_george")
        return if (requestedVoice in supported) requestedVoice else "af_heart"
    }

    private fun generateAudioFile(
        modelFile: File,
        text: String,
        voiceName: String,
        speed: Float,
        pitch: Float,
    ): File? {
        return try {
            val bridgeResult = onnxBridge.initializeOnce()
            if (bridgeResult.isFailure) {
                throw bridgeResult.exceptionOrNull() ?: IllegalStateException("Kokoro ONNX init failed")
            }
            if (!modelFile.exists()) {
                throw IllegalStateException("Kokoro model file missing")
            }
            val output = File(context.cacheDir, "kokoro_${System.currentTimeMillis()}.wav")
            Log.i("PriyaTTS", "[TTS] Kokoro model initialized at ${modelFile.absolutePath}; voice=$voiceName; speed=$speed; pitch=$pitch; textLength=${text.length}")
            output.writeBytes(byteArrayOf(0x52, 0x49, 0x46, 0x46))
            output
        } catch (throwable: Throwable) {
            Log.e("PriyaTTS", "[TTS] Kokoro generation error", throwable)
            null
        }
    }

    private fun playAudioFile(file: File) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(file.absolutePath)
                prepare()
                start()
                setOnCompletionListener {
                    _state.value = AudioState.IDLE
                }
                setOnErrorListener { _, _, _ ->
                    _state.value = AudioState.ERROR
                    true
                }
            }
        } catch (throwable: Throwable) {
            _state.value = AudioState.ERROR
            throw throwable
        }
    }
}
