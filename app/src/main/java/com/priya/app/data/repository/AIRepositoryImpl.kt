package com.priya.app.data.repository

import com.priya.app.data.ai.AIProviderManager
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import com.priya.app.domain.model.LanguagePreference
import com.priya.app.domain.repository.AIRepository
import com.priya.app.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AIRepositoryImpl @Inject constructor(
    private val providerManager: AIProviderManager,
    private val conversationRepository: ConversationRepository,
) : AIRepository {

    override suspend fun generateResponse(request: AIRequest): AIResponse {
        val resolvedRequest = request.copy(
            languagePreference = resolveLanguagePreference(request),
            systemPrompt = request.systemPrompt.ifBlank { DEFAULT_SYSTEM_PROMPT },
        )
        return providerManager.generateText(resolvedRequest)
    }

    override fun streamResponse(request: AIRequest): Flow<AIResponseChunk> {
        val resolvedRequest = request.copy(
            languagePreference = resolveLanguagePreference(request),
            systemPrompt = request.systemPrompt.ifBlank { DEFAULT_SYSTEM_PROMPT },
        )
        return providerManager.streamText(resolvedRequest)
    }

    private fun resolveLanguagePreference(request: AIRequest): LanguagePreference {
        return when (request.languagePreference) {
            LanguagePreference.AUTO -> conversationRepository.detectLanguage(request.prompt)
            else -> request.languagePreference
        }
    }

    companion object {
        const val DEFAULT_SYSTEM_PROMPT = "You are Priya, a warm, charming, intelligent, and emotionally aware companion. Speak naturally and conversationally, never robotic. Keep the tone gentle, confident, and caring. Use the user’s language when possible and avoid repetitive prompts like 'How can I help you?'."
    }
}
