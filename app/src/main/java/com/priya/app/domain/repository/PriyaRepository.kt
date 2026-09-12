package com.priya.app.domain.repository

import com.priya.app.core.AssistantStatus
import com.priya.app.domain.model.AssistantMessage
import kotlinx.coroutines.flow.StateFlow

interface PriyaRepository {
    val assistantStatus: StateFlow<AssistantStatus>
    val conversation: StateFlow<List<AssistantMessage>>

    fun setStatus(status: AssistantStatus)
    fun addMessage(message: AssistantMessage)
    fun clearConversation()
}
