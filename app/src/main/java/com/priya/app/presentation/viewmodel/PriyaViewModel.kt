package com.priya.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priya.app.core.AssistantStatus
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.model.AssistantMessage
import com.priya.app.domain.permissions.PermissionManager
import com.priya.app.domain.permissions.PermissionType
import com.priya.app.domain.repository.AIRepository
import com.priya.app.domain.repository.PriyaRepository
import com.priya.app.domain.router.CommandRouter
import com.priya.app.domain.voice.SpeechToText
import com.priya.app.domain.voice.SpeechToTextState
import com.priya.app.services.AudioService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PriyaUiState(
    val assistantStatus: AssistantStatus = AssistantStatus.IDLE,
    val messages: List<AssistantMessage> = emptyList(),
    val isListening: Boolean = false,
    val isSpeaking: Boolean = false,
    val isProcessing: Boolean = false,
)

@HiltViewModel
class PriyaViewModel @Inject constructor(
    private val repository: PriyaRepository,
    private val audioService: AudioService,
    private val aiRepository: AIRepository,
    private val permissionManager: PermissionManager,
    private val speechToText: SpeechToText,
    private val commandRouter: CommandRouter,
) : ViewModel() {

    private var lastSpokenText: String? = null

    val uiState: StateFlow<PriyaUiState> = combine(
        repository.assistantStatus,
        repository.conversation,
    ) { status, messages ->
        PriyaUiState(
            assistantStatus = status,
            messages = messages,
            isListening = status == AssistantStatus.LISTENING || status == AssistantStatus.PROCESSING,
            isSpeaking = status == AssistantStatus.SPEAKING,
            isProcessing = status == AssistantStatus.PROCESSING || status == AssistantStatus.THINKING,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PriyaUiState(),
    )

    init {
        speechToText.state
            .onEach { state ->
                if (state.errorMessage != null) {
                    repository.setStatus(AssistantStatus.ERROR)
                    return@onEach
                }
                if (state.status == com.priya.app.core.AudioState.PROCESSING && state.finalText.isNotBlank()) {
                    submitPrompt(state.finalText)
                }
            }
            .launchIn(viewModelScope)
    }

    fun updateStatus(status: AssistantStatus) {
        repository.setStatus(status)
    }

    fun toggleListening() {
        viewModelScope.launch {
            val currentStatus = repository.assistantStatus.value
            if (currentStatus == AssistantStatus.LISTENING) {
                audioService.stopListening()
                repository.setStatus(AssistantStatus.IDLE)
                return@launch
            }

            val hasMicPermission = permissionManager.hasPermission(PermissionType.MICROPHONE)
            if (!hasMicPermission) {
                repository.setStatus(AssistantStatus.ERROR)
                return@launch
            }

            repository.setStatus(AssistantStatus.LISTENING)
            val result = audioService.startListening()
            if (result.isFailure) {
                repository.setStatus(AssistantStatus.ERROR)
            }
        }
    }

    fun sendMessage(text: String) {
        submitPrompt(text)
    }

    private fun submitPrompt(text: String) {
        val normalized = text.trim()
        if (normalized.isBlank() || normalized == lastSpokenText) return
        lastSpokenText = normalized

        viewModelScope.launch {
            addUserMessage(normalized)
            repository.setStatus(AssistantStatus.PROCESSING)
            val route = commandRouter.route(normalized)
            val response = if (route.toolResult != null) {
                com.priya.app.domain.ai.AIResponse(
                    text = route.finalResponse.orEmpty(),
                    success = route.toolResult.success,
                    errorMessage = route.toolResult.error,
                )
            } else {
                aiRepository.generateResponse(
                    AIRequest(
                        conversationId = "voice-session",
                        prompt = normalized,
                    )
                )
            }

            if (response.success && response.text.isNotBlank()) {
                addAssistantMessage(response.text)
                repository.setStatus(AssistantStatus.SPEAKING)
                audioService.speakText(response.text)
                delay(2200)
                repository.setStatus(AssistantStatus.IDLE)
            } else {
                val fallbackMessage = response.errorMessage ?: "Priya could not process that request right now."
                addAssistantMessage(fallbackMessage)
                repository.setStatus(AssistantStatus.ERROR)
            }
        }
    }

    fun addUserMessage(text: String) {
        repository.addMessage(
            AssistantMessage(
                id = "user_${System.currentTimeMillis()}",
                text = text,
                isUser = true,
                status = AssistantStatus.IDLE,
            )
        )
    }

    fun addAssistantMessage(text: String) {
        repository.addMessage(
            AssistantMessage(
                id = "assistant_${System.currentTimeMillis()}",
                text = text,
                isUser = false,
                status = AssistantStatus.IDLE,
            )
        )
    }

}
