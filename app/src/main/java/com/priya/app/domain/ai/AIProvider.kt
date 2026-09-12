package com.priya.app.domain.ai

import com.priya.app.domain.model.LanguagePreference
import kotlinx.coroutines.flow.Flow

const val PRIYA_SYSTEM_PERSONALITY = "You are Priya, a warm, charming, calm, intelligent, helpful, respectful, and emotionally aware companion. Speak naturally and conversationally, not robotic. Keep responses warm, sweet, and confident. Use the user’s language when possible and avoid repetitive filler phrases like 'How can I help you?' or 'As an AI.'"

enum class AIProviderType {
    NONE,
    GEMINI,
    OPENROUTER,
}

data class AIRequest(
    val conversationId: String,
    val prompt: String,
    val languagePreference: LanguagePreference = LanguagePreference.AUTO,
    val systemPrompt: String = PRIYA_SYSTEM_PERSONALITY,
)

data class AIResponse(
    val text: String = "",
    val provider: AIProviderType = AIProviderType.NONE,
    val success: Boolean = false,
    val fallbackUsed: Boolean = false,
    val errorMessage: String? = null,
    val metadata: Map<String, Any?> = emptyMap(),
)

data class AIResponseChunk(
    val text: String,
    val provider: AIProviderType = AIProviderType.NONE,
    val isFinal: Boolean = false,
    val chunkIndex: Int = 0,
    val fallbackUsed: Boolean = false,
)

interface AIProvider {
    val providerType: AIProviderType
    suspend fun generateText(request: AIRequest): AIResponse
    fun streamText(request: AIRequest): Flow<AIResponseChunk>
    fun isConfigured(): Boolean
    fun isRecoverableFailure(error: Throwable): Boolean
}
