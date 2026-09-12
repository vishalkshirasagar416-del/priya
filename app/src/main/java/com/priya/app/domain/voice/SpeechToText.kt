package com.priya.app.domain.voice

import com.priya.app.core.AudioState
import kotlinx.coroutines.flow.StateFlow

interface SpeechToText {
    val state: StateFlow<SpeechToTextState>

    suspend fun startListening(language: String = "en-US"): Result<Unit>
    suspend fun stopListening(): Result<Unit>
    suspend fun shutdown(): Result<Unit>
    fun prepareForLanguage(language: String)
}

data class SpeechToTextState(
    val status: AudioState = AudioState.IDLE,
    val partialText: String = "",
    val finalText: String = "",
    val errorMessage: String? = null,
    val language: String = "en-US",
)
