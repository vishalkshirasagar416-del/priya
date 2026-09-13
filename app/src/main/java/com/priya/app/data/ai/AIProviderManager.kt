package com.priya.app.data.ai

import android.util.Log
import com.priya.app.domain.ai.AIProvider
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import com.priya.app.domain.repository.AIConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

private const val TAG = "PriyaAI"

@Singleton
class AIProviderManager @Inject constructor(
    @Named("gemini") private val geminiProvider: AIProvider,
    @Named("openRouter") private val openRouterProvider: AIProvider,
    private val configRepository: AIConfigRepository,
) {

    suspend fun generateText(request: AIRequest): AIResponse {
        val providers = orderedProviders()
        providers.forEachIndexed { index, provider ->
            val response = tryProvider(provider, request)
            if (response != null && response.success && response.text.isNotBlank()) {
                return response.copy(fallbackUsed = index > 0)
            }
        }

        return AIResponse(
            text = "Priya is currently offline, but I’m here with you. Please try again in a moment.",
            provider = AIProviderType.NONE,
            success = false,
            fallbackUsed = false,
            errorMessage = "AI service is temporarily unavailable. Please try again.",
            metadata = mapOf("offline" to true, "reason" to "provider_failure"),
        )
    }

    fun streamText(request: AIRequest): Flow<AIResponseChunk> = flow {
        orderedProviders().forEachIndexed { index, provider ->
            val stream = runCatching { provider.streamText(request) }.getOrNull() ?: return@forEachIndexed
            var emitted = false
            stream.collect { chunk ->
                emitted = true
                emit(chunk.copy(fallbackUsed = index > 0))
            }
            if (emitted) return@flow
        }

        emit(
            AIResponseChunk(
                text = "Priya is currently offline, but I’m here with you.",
                provider = AIProviderType.NONE,
                isFinal = true,
                chunkIndex = 0,
            )
        )
    }.catch { throwable ->
        Log.w(TAG, "Streaming provider failure: ${sanitizeForLogs(throwable.message)}")
        emit(
            AIResponseChunk(
                text = "Priya is currently offline, but I’m here with you.",
                provider = AIProviderType.NONE,
                isFinal = true,
                chunkIndex = 0,
            )
        )
    }

    private suspend fun tryProvider(provider: AIProvider, request: AIRequest): AIResponse? {
        if (!provider.isConfigured()) {
            Log.d(TAG, "Provider ${provider.providerType.name} is not configured.")
            return null
        }

        var lastError: Throwable? = null
        for (attempt in 1..2) {
            try {
                val response = withTimeout(20_000L) {
                    provider.generateText(request)
                }
                if (response.success && response.text.isNotBlank()) {
                    return response
                }
                return null
            } catch (throwable: Throwable) {
                lastError = throwable
                val recoverable = provider.isRecoverableFailure(throwable)
                if (!recoverable || attempt == 2) {
                    val errorMessage = sanitizeForLogs(throwable.message)
                    Log.w(TAG, "Provider ${provider.providerType.name} failed on attempt $attempt: $errorMessage")
                    return null
                }
            }
        }

        return null
    }

    private fun orderedProviders(): List<AIProvider> {
        val primary = configRepository.getConfig().primaryProvider
        return if (primary == AIProviderType.OPENROUTER) {
            listOf(openRouterProvider, geminiProvider)
        } else {
            listOf(geminiProvider, openRouterProvider)
        }
    }

    private fun sanitizeForLogs(message: String?): String {
        if (message.isNullOrBlank()) return "unknown error"
        return message
            .replace(Regex("(?i)(key=|Bearer )[^\\s&]+"), "$1[REDACTED]")
            .replace(Regex("(?:AIza|sk-)[A-Za-z0-9_\\-]+"), "[REDACTED]")
    }
}
