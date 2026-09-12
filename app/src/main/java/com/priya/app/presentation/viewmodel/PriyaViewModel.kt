package com.priya.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priya.app.core.AssistantStatus
import com.priya.app.domain.model.AssistantMessage
import com.priya.app.domain.repository.PriyaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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
) : ViewModel() {

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

    fun updateStatus(status: AssistantStatus) {
        repository.setStatus(status)
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
