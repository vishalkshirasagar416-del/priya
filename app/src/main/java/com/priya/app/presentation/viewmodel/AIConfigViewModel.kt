package com.priya.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.repository.AIConfig
import com.priya.app.domain.repository.AIConfigRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class AIConfigUiState(
    val config: AIConfig = AIConfig(),
    val error: String? = null,
)

@HiltViewModel
class AIConfigViewModel @Inject constructor(
    private val repository: AIConfigRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AIConfigUiState(repository.getConfig()))
    val state: StateFlow<AIConfigUiState> = _state.asStateFlow()

    fun save(
        gemini: String,
        openRouter: String,
        elevenLabs: String,
        primaryProvider: AIProviderType,
        requireProvider: Boolean,
    ): Boolean {
        val current = repository.getConfig()
        val next = current.copy(
            geminiApiKey = gemini.trim().ifBlank { current.geminiApiKey },
            openRouterApiKey = openRouter.trim().ifBlank { current.openRouterApiKey },
            elevenLabsApiKey = elevenLabs.trim().ifBlank { current.elevenLabsApiKey },
            primaryProvider = primaryProvider,
            setupCompleted = true,
        )
        if (requireProvider && next.geminiApiKey.isBlank() && next.openRouterApiKey.isBlank()) {
            _state.value = _state.value.copy(error = "Add at least one Gemini or OpenRouter API key to continue.")
            return false
        }
        repository.saveConfig(next)
        _state.value = AIConfigUiState(repository.getConfig())
        return true
    }

    fun skipSetup() {
        val current = repository.getConfig()
        repository.saveConfig(current.copy(setupCompleted = true))
        _state.value = AIConfigUiState(repository.getConfig())
    }

    fun remove(provider: AIProviderType) {
        repository.clearProvider(provider)
        _state.value = AIConfigUiState(repository.getConfig())
    }
}