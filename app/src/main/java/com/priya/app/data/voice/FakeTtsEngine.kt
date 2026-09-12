package com.priya.app.data.voice

import com.priya.app.core.AudioState
import com.priya.app.domain.voice.TextToSpeechEngine
import com.priya.app.domain.voice.VoiceContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeTtsEngine(
    override val name: String,
    private val available: Boolean = true,
    private val fail: Boolean = false,
) : TextToSpeechEngine {
    private val _state = MutableStateFlow(AudioState.IDLE)
    override val state: StateFlow<AudioState> = _state.asStateFlow()
    override val isConfigured: Boolean = available

    override suspend fun speak(text: String, context: VoiceContext): Result<Unit> {
        return if (fail) Result.failure(IllegalStateException("simulated failure")) else Result.success(Unit)
    }

    override suspend fun stop(): Result<Unit> = Result.success(Unit)
    override suspend fun shutdown(): Result<Unit> = Result.success(Unit)
    override fun supportsLanguage(languageCode: String): Boolean = true
    override fun shouldInterruptOn(text: String): Boolean = false
}
