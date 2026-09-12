package com.priya.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priya.app.data.voice.KokoroModelManager
import com.priya.app.data.voice.VoicePreferences
import com.priya.app.domain.voice.VoiceEnginePreference
import com.priya.app.domain.voice.VoiceSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoiceSettingsViewModel @Inject constructor(
    private val preferences: VoicePreferences,
    private val kokoroModelManager: KokoroModelManager,
) : ViewModel() {
    private val _settings = MutableStateFlow(preferences.getSettings())
    val settings: StateFlow<VoiceSettings> = _settings.asStateFlow()

    val modelState = kokoroModelManager.state

    fun setVoiceEngine(value: VoiceEnginePreference) {
        preferences.updateSettings { it.copy(selectedVoiceEngine = value) }
        _settings.value = preferences.getSettings()
    }

    fun setKokoroVoice(value: String) {
        val safe = value.ifBlank { "af_heart" }
        preferences.updateSettings { it.copy(kokoroVoice = safe) }
        _settings.value = preferences.getSettings()
    }

    fun setVoiceSpeed(value: Float) {
        preferences.updateSettings { it.copy(voiceSpeed = value) }
        _settings.value = preferences.getSettings()
    }

    fun setVoicePitch(value: Float) {
        preferences.updateSettings { it.copy(voicePitch = value) }
        _settings.value = preferences.getSettings()
    }

    fun ensureKokoroModel() {
        viewModelScope.launch {
            runCatching { kokoroModelManager.ensureModel() }
        }
    }
}
