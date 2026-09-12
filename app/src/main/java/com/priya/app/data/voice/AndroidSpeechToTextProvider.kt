package com.priya.app.data.voice

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.annotation.RequiresPermission
import com.priya.app.core.AudioState
import com.priya.app.domain.voice.SpeechToText
import com.priya.app.domain.voice.SpeechToTextState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidSpeechToTextProvider @Inject constructor(
    private val context: Context,
) : SpeechToText {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var recognizer: SpeechRecognizer? = null
    private val _state = MutableStateFlow(SpeechToTextState())
    override val state: StateFlow<SpeechToTextState> = _state.asStateFlow()

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    override suspend fun startListening(language: String): Result<Unit> {
        if (!hasPermission()) {
            _state.value = _state.value.copy(
                status = AudioState.ERROR,
                errorMessage = "Microphone permission is required to listen."
            )
            return Result.failure(SecurityException("Microphone permission is required"))
        }

        val recognizerInstance = SpeechRecognizer.createSpeechRecognizer(context)
        recognizer = recognizerInstance
        recognizerInstance.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                _state.value = _state.value.copy(status = AudioState.LISTENING, errorMessage = null)
            }

            override fun onBeginningOfSpeech() {
                _state.value = _state.value.copy(status = AudioState.LISTENING)
            }

            override fun onRmsChanged(rmsdB: Float) = Unit

            override fun onBufferReceived(buffer: ByteArray?) = Unit

            override fun onPartialResults(partialResults: Bundle?) {
                val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val partialText = matches?.firstOrNull().orEmpty()
                if (partialText.isNotBlank()) {
                    _state.value = _state.value.copy(partialText = partialText, status = AudioState.LISTENING)
                }
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val finalText = matches?.firstOrNull().orEmpty()
                _state.value = _state.value.copy(
                    status = AudioState.PROCESSING,
                    finalText = finalText,
                    partialText = "",
                    errorMessage = null,
                )
                scope.launch { stopListening() }
            }

            override fun onError(error: Int) {
                _state.value = _state.value.copy(
                    status = AudioState.ERROR,
                    errorMessage = mapError(error),
                    partialText = "",
                )
            }

            override fun onEndOfSpeech() {
                _state.value = _state.value.copy(status = AudioState.PROCESSING)
            }

            override fun onEvent(eventType: Int, params: Bundle?) = Unit
        })

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to Priya")
        }

        recognizerInstance.startListening(intent)
        _state.value = _state.value.copy(status = AudioState.LISTENING, language = language)
        return Result.success(Unit)
    }

    override suspend fun stopListening(): Result<Unit> {
        return try {
            recognizer?.stopListening()
            recognizer?.destroy()
            recognizer = null
            _state.value = _state.value.copy(status = AudioState.IDLE)
            Result.success(Unit)
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }

    override suspend fun shutdown(): Result<Unit> {
        scope.cancel()
        recognizer?.destroy()
        recognizer = null
        _state.value = SpeechToTextState(status = AudioState.IDLE)
        return Result.success(Unit)
    }

    override fun prepareForLanguage(language: String) {
        _state.value = _state.value.copy(language = language)
    }

    private fun hasPermission(): Boolean {
        return context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    private fun mapError(error: Int): String {
        return when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Speech recognition client error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Microphone permission required"
            SpeechRecognizer.ERROR_NETWORK -> "Network unavailable"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Speech recognition timed out"
            SpeechRecognizer.ERROR_NO_MATCH -> "No speech recognized"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Speech recognizer is busy"
            SpeechRecognizer.ERROR_SERVER -> "Service unavailable"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech detected"
            else -> "Speech recognition error"
        }
    }
}
