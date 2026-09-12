package com.priya.app.domain.repository

import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import kotlinx.coroutines.flow.Flow

interface AIRepository {
    suspend fun generateResponse(request: AIRequest): AIResponse
    fun streamResponse(request: AIRequest): Flow<AIResponseChunk>
}
