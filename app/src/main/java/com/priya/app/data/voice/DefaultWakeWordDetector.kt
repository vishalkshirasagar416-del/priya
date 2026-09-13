package com.priya.app.data.voice

import com.priya.app.domain.voice.PriyaWakeWords
import com.priya.app.domain.voice.WakeWordDetector
import com.priya.app.domain.voice.WakeWordEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultWakeWordDetector @Inject constructor() : WakeWordDetector {
    override val defaultWakeWords: List<String> = PriyaWakeWords.DEFAULT
    override val isAvailable: Boolean = true

    override fun startListening(): Result<Unit> = Result.success(Unit)

    override fun stopListening(): Result<Unit> = Result.success(Unit)

    override fun detectInAudio(audioText: String): Boolean {
        val normalized = audioText.trim()
        return defaultWakeWords.any { wakeWord ->
            normalized.contains(wakeWord, ignoreCase = true)
        }
    }

    override fun observeWakeWords(): Flow<String> = flowOf()
}
