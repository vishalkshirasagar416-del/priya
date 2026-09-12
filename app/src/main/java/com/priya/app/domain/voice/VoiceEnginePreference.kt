package com.priya.app.domain.voice

enum class VoiceEnginePreference {
    AUTO,
    ELEVENLABS,
    KOKORO,
    ANDROID_LOCAL,
}

data class VoiceSettings(
    val selectedVoiceEngine: VoiceEnginePreference = VoiceEnginePreference.AUTO,
    val kokoroVoice: String = "af_heart",
    val voiceSpeed: Float = 1.0f,
    val voicePitch: Float = 1.0f,
)
