package com.priya.app.data.voice

import android.content.Context
import androidx.core.content.edit
import com.priya.app.domain.voice.VoiceEnginePreference
import com.priya.app.domain.voice.VoiceSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoicePreferences @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val prefs = context.getSharedPreferences("priya_voice_settings", Context.MODE_PRIVATE)

    fun getSettings(): VoiceSettings = VoiceSettings(
        selectedVoiceEngine = prefs.getString("voice_engine", VoiceEnginePreference.AUTO.name)
            ?.let { runCatching { VoiceEnginePreference.valueOf(it) }.getOrDefault(VoiceEnginePreference.AUTO) }
            ?: VoiceEnginePreference.AUTO,
        kokoroVoice = prefs.getString("kokoro_voice", "af_heart") ?: "af_heart",
        voiceSpeed = prefs.getFloat("voice_speed", 1.0f),
        voicePitch = prefs.getFloat("voice_pitch", 1.0f),
    )

    fun updateSettings(transform: (VoiceSettings) -> VoiceSettings) {
        val updated = transform(getSettings())
        prefs.edit {
            putString("voice_engine", updated.selectedVoiceEngine.name)
            putString("kokoro_voice", updated.kokoroVoice)
            putFloat("voice_speed", updated.voiceSpeed)
            putFloat("voice_pitch", updated.voicePitch)
        }
    }
}
