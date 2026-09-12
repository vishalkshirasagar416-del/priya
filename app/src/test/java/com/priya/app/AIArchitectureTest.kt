package com.priya.app

import com.priya.app.data.ai.AIProviderManager
import com.priya.app.domain.ai.AIProvider
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import com.priya.app.domain.model.Conversation
import com.priya.app.domain.model.LanguagePreference
import com.priya.app.domain.model.Message
import com.priya.app.domain.model.Role
import com.priya.app.domain.repository.ConversationRepository
import com.priya.app.data.repository.InMemoryConversationRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AIArchitectureTest {

    @Test
    fun providerSelection_prefersGemini_whenAvailable() = runTest {
        val manager = AIProviderManager(
            geminiProvider = FakeGeminiProvider(result = AIResponse("Gemini reply", AIProviderType.GEMINI, true)),
            openRouterProvider = FakeOpenRouterProvider(result = AIResponse("OpenRouter reply", AIProviderType.OPENROUTER, true)),
        )

        val response = manager.generateText(AIRequest("c1", "Hello Priya"))

        assertEquals(AIProviderType.GEMINI, response.provider)
        assertTrue(response.success)
    }

    @Test
    fun geminiFailure_fallsBackToOpenRouter() = runTest {
        val manager = AIProviderManager(
            geminiProvider = FakeGeminiProvider(failure = RuntimeException("429 rate limited"), recoverable = true),
            openRouterProvider = FakeOpenRouterProvider(result = AIResponse("Fallback response", AIProviderType.OPENROUTER, true, fallbackUsed = true)),
        )

        val response = manager.generateText(AIRequest("c2", "I'm tired"))

        assertEquals(AIProviderType.OPENROUTER, response.provider)
        assertTrue(response.success)
        assertTrue(response.fallbackUsed)
    }

    @Test
    fun successfulGeminiResponse_isReturnedWithoutFallback() = runTest {
        val manager = AIProviderManager(
            geminiProvider = FakeGeminiProvider(result = AIResponse("Warm reply", AIProviderType.GEMINI, true)),
            openRouterProvider = FakeOpenRouterProvider(result = AIResponse("Should not be used", AIProviderType.OPENROUTER, true)),
        )

        val response = manager.generateText(AIRequest("c3", "Tell me something warm"))

        assertEquals(AIProviderType.GEMINI, response.provider)
        assertFalse(response.fallbackUsed)
    }

    @Test
    fun failedProviders_returnFriendlyOfflineResponse() = runTest {
        val manager = AIProviderManager(
            geminiProvider = FakeGeminiProvider(failure = RuntimeException("No network"), recoverable = false),
            openRouterProvider = FakeOpenRouterProvider(failure = RuntimeException("Server down"), recoverable = false),
        )

        val response = manager.generateText(AIRequest("c4", "Hello"))

        assertFalse(response.success)
        assertEquals(AIProviderType.NONE, response.provider)
        assertTrue(response.errorMessage!!.contains("offline", ignoreCase = true) || response.errorMessage.contains("service", ignoreCase = true))
    }

    @Test
    fun conversationState_tracksMessagesAndLanguage() = runTest {
        val repository: ConversationRepository = InMemoryConversationRepository()
        val conversation = Conversation(
            id = "conv-1",
            title = "Chat",
            languagePreference = LanguagePreference.AUTO,
        )
        repository.saveConversation(conversation)

        repository.addMessage(
            conversation.id,
            Message(
                id = "m1",
                conversationId = conversation.id,
                role = Role.USER,
                content = "Namaskara, how are you?",
            )
        )

        val stored = repository.getConversation(conversation.id)
        assertEquals(1, stored?.messages?.size)
        assertEquals("Namaskara, how are you?", stored?.messages?.first()?.content)

        repository.updateLanguagePreference(conversation.id, LanguagePreference.KANNADA)
        assertEquals(LanguagePreference.KANNADA, repository.getConversation(conversation.id)?.languagePreference)
    }

    @Test
    fun languagePreference_detectsHinglish_andKannada() {
        val repository: ConversationRepository = InMemoryConversationRepository()

        assertEquals(LanguagePreference.HINGLISH, repository.detectLanguage("Namaste yaar, what a lovely day"))
        assertEquals(LanguagePreference.KANNADA, repository.detectLanguage("ನಮಸ್ಕಾರ, ಹೇಗಿದ್ದೀರಿ?"))
        assertEquals(LanguagePreference.HINDI, repository.detectLanguage("अरे, आज बहुत अच्छा लग रहा है"))
    }

    @Test
    fun streamingResponse_emitsPartialText() = runTest {
        val manager = AIProviderManager(
            geminiProvider = FakeGeminiProvider(
                streamingChunks = listOf(
                    AIResponseChunk("Priya begins", AIProviderType.GEMINI, isFinal = false),
                    AIResponseChunk(" speaking softly.", AIProviderType.GEMINI, isFinal = true)
                )
            ),
            openRouterProvider = FakeOpenRouterProvider(),
        )

        val chunks = manager.streamText(AIRequest("c5", "Tell me a calm response")).toList()
        assertTrue(chunks.size >= 2)
        assertEquals(AIProviderType.GEMINI, chunks.first().provider)
        assertTrue(chunks.any { it.text.contains("Priya") })
    }
}

private class FakeGeminiProvider(
    private val result: AIResponse? = null,
    private val failure: RuntimeException? = null,
    private val recoverable: Boolean = false,
    private val streamingChunks: List<AIResponseChunk> = emptyList(),
) : AIProvider {
    override val providerType: AIProviderType = AIProviderType.GEMINI

    override suspend fun generateText(request: AIRequest): AIResponse {
        failure?.let { throw it }
        return result ?: AIResponse("", AIProviderType.GEMINI, false)
    }

    override fun streamText(request: AIRequest): kotlinx.coroutines.flow.Flow<AIResponseChunk> {
        return kotlinx.coroutines.flow.flowOf(*streamingChunks.toTypedArray())
    }

    override fun isConfigured(): Boolean = true

    override fun isRecoverableFailure(error: Throwable): Boolean = recoverable
}

private class FakeOpenRouterProvider(
    private val result: AIResponse? = null,
    private val failure: RuntimeException? = null,
    private val recoverable: Boolean = false,
    private val streamingChunks: List<AIResponseChunk> = emptyList(),
) : AIProvider {
    override val providerType: AIProviderType = AIProviderType.OPENROUTER

    override suspend fun generateText(request: AIRequest): AIResponse {
        failure?.let { throw it }
        return result ?: AIResponse("", AIProviderType.OPENROUTER, false)
    }

    override fun streamText(request: AIRequest): kotlinx.coroutines.flow.Flow<AIResponseChunk> {
        return kotlinx.coroutines.flow.flowOf(*streamingChunks.toTypedArray())
    }

    override fun isConfigured(): Boolean = true

    override fun isRecoverableFailure(error: Throwable): Boolean = recoverable
}
