package com.priya.app.domain.model

import androidx.compose.ui.graphics.Color
import com.priya.app.core.AssistantStatus

data class AssistantMessage(
    val id: String,
    val text: String,
    val isUser: Boolean = false,
    val status: AssistantStatus = AssistantStatus.IDLE,
    val timestamp: Long = System.currentTimeMillis(),
    val accentColor: Color? = null,
)
