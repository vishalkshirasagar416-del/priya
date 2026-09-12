package com.priya.app.data.repository

import com.priya.app.core.AssistantStatus
import com.priya.app.domain.model.AssistantMessage
import com.priya.app.domain.repository.PriyaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultPriyaRepository @Inject constructor() : PriyaRepository {
    private val _assistantStatus = MutableStateFlow(AssistantStatus.IDLE)
    private val _conversation = MutableStateFlow<List<AssistantMessage>>(emptyList())

    override val assistantStatus: StateFlow<AssistantStatus> = _assistantStatus.asStateFlow()
    override val conversation: StateFlow<List<AssistantMessage>> = _conversation.asStateFlow()

    override fun setStatus(status: AssistantStatus) {
        _assistantStatus.value = status
    }

    override fun addMessage(message: AssistantMessage) {
        _conversation.value = _conversation.value + message
    }

    override fun clearConversation() {
        _conversation.value = emptyList()
    }
}
