package com.priya.app.domain.voice

import com.priya.app.core.AudioState
import kotlinx.coroutines.flow.StateFlow

interface TextToSpeechEngine {
    val state: StateFlow<AudioState>
    val isConfigured: Boolean
    val name: String

    suspend fun speak(text: String, context: VoiceContext = VoiceContext.GENERAL): Result<Unit>
    suspend fun stop(): Result<Unit>
    suspend fun shutdown(): Result<Unit>
    fun supportsLanguage(languageCode: String): Boolean
    fun shouldInterruptOn(text: String): Boolean
}

enum class VoiceContext {
    GENERAL,
    CALM,
    EXCITED,
    COMFORTING,
    SHORT,
    SERIAL,
}

data class VoiceStyle(
    val voiceName: String = "Priya",
    val gender: String = "female",
    val pitch: Float = 1.0f,
    val speakingRate: Float = 1.0f,
    val volume: Float = 1.0f,
    val style: VoiceContext = VoiceContext.GENERAL,
)
