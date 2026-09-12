package com.priya.app.data.ai

import android.util.Log
import com.priya.app.domain.ai.AIProvider
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
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
) {

    suspend fun generateText(request: AIRequest): AIResponse {
        val geminiResponse = tryProvider(geminiProvider, request)
        if (geminiResponse != null && geminiResponse.success && geminiResponse.text.isNotBlank()) {
            return geminiResponse.copy(fallbackUsed = false)
        }

        val openRouterResponse = tryProvider(openRouterProvider, request)
        if (openRouterResponse != null && openRouterResponse.success && openRouterResponse.text.isNotBlank()) {
            return openRouterResponse.copy(fallbackUsed = geminiResponse != null && !geminiResponse.success)
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
        val geminiStream = runCatching { geminiProvider.streamText(request) }
        if (geminiStream.isSuccess) {
            val stream = geminiStream.getOrThrow()
            var emitted = false
            stream.collect { chunk ->
                emitted = true
                emit(chunk)
            }
            if (emitted) return@flow
        }

        val openRouterStream = runCatching { openRouterProvider.streamText(request) }
        if (openRouterStream.isSuccess) {
            openRouterStream.getOrThrow().collect { chunk ->
                emit(chunk.copy(fallbackUsed = chunk.provider == AIProviderType.OPENROUTER))
            }
            return@flow
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

    private fun sanitizeForLogs(message: String?): String {
        if (message.isNullOrBlank()) return "unknown error"
        return message
            .replace(Regex("(?i)(key=|Bearer )[^\\s&]+"), "$1[REDACTED]")
            .replace(Regex("(?:AIza|sk-)[A-Za-z0-9_\\-]+"), "[REDACTED]")
    }
}
