package com.priya.app.domain.voice

import com.priya.app.core.AudioState
import kotlinx.coroutines.flow.Flow

interface WakeWordDetector {
    val defaultWakeWords: List<String>
    val isAvailable: Boolean

    fun startListening(): Result<Unit>
    fun stopListening(): Result<Unit>
    fun detectInAudio(audioText: String): Boolean
    fun observeWakeWords(): Flow<String>
}

object PriyaWakeWords {
    val DEFAULT = listOf("Priya", "Hey Priya", "Hi Priya")
}

sealed class WakeWordEvent {
    data class Detected(val wakeWord: String) : WakeWordEvent()
    data class Error(val message: String) : WakeWordEvent()
    data object Idle : WakeWordEvent()
}
