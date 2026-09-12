package com.priya.app

import com.priya.app.domain.voice.VoiceEnginePreference
import com.priya.app.domain.voice.VoiceSettings
import com.priya.app.data.voice.FakeTtsEngine
import com.priya.app.data.voice.TtsManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TtsManagerTest {

    @Test
    fun autoMode_prefersElevenLabs_thenKokoro_thenLocal() = runTest {
        val manager = TtsManager(
            enginePreference = VoiceEnginePreference.AUTO,
            engines = listOf(
                FakeTtsEngine("ElevenLabs", available = false, fail = true),
                FakeTtsEngine("Kokoro", available = true),
                FakeTtsEngine("AndroidLocal", available = true),
            )
        )

        val result = manager.selectEngineForText("hello")
        assertEquals("Kokoro", result.name)
    }

    @Test
    fun explicitKokoroMode_usesKokoroBeforeLocalFallback() = runTest {
        val manager = TtsManager(
            enginePreference = VoiceEnginePreference.KOKORO,
            engines = listOf(
                FakeTtsEngine("Kokoro", available = false, fail = true),
                FakeTtsEngine("AndroidLocal", available = true),
            )
        )

        val result = manager.selectEngineForText("hello")
        assertEquals("AndroidLocal", result.name)
        assertTrue(manager.getPriorityOrder().contains("Kokoro"))
    }

    @Test
    fun voiceSettings_keepKokoroVoiceChoice() = runTest {
        val settings = VoiceSettings(
            selectedVoiceEngine = VoiceEnginePreference.AUTO,
            kokoroVoice = "af_heart",
            voiceSpeed = 1.0f,
            voicePitch = 1.0f,
        )

        assertEquals("af_heart", settings.kokoroVoice)
    }
}
