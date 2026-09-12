package com.priya.app.data.ai.backend

import com.priya.app.data.security.BackendSecurityConfigProvider
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import com.priya.app.domain.ai.AIProviderType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

interface BackendApi {
    suspend fun chat(request: AIRequest): AIResponse
    fun streamChat(request: AIRequest): Flow<AIResponseChunk>
    suspend fun healthCheck(): Boolean
}

@Singleton
class DefaultBackendApi @Inject constructor(
    private val backendConfigProvider: BackendSecurityConfigProvider,
) : BackendApi {

    override suspend fun chat(request: AIRequest): AIResponse = withContext(Dispatchers.IO) {
        val config = backendConfigProvider.provide()
        val baseUrl = config.baseUrl.trimEnd('/').ifBlank { "https://api.priya.app" }

        if (!baseUrl.startsWith("https://")) {
            return@withContext AIResponse(
                text = "",
                provider = AIProviderType.NONE,
                success = false,
                errorMessage = "AI service is temporarily unavailable. Please try again.",
                metadata = mapOf("backend" to false, "reason" to "invalid_backend_url"),
            )
        }

        try {
            val url = URL("$baseUrl/api/v1/chat")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Authorization", "Bearer ${config.apiKey}")
            connection.connectTimeout = TimeUnit.SECONDS.toMillis(15).toInt()
            connection.readTimeout = TimeUnit.SECONDS.toMillis(15).toInt()
            connection.doOutput = true

            val payload = JSONObject().apply {
                put("conversationId", request.conversationId)
                put("prompt", request.prompt)
                put("systemPrompt", request.systemPrompt)
                put("languagePreference", request.languagePreference.name)
            }.toString()

            OutputStreamWriter(connection.outputStream, Charsets.UTF_8).use { writer ->
                writer.write(payload)
                writer.flush()
            }

            val responseCode = connection.responseCode
            if (responseCode !in 200..299) {
                return@withContext AIResponse(
                    text = "",
                    provider = AIProviderType.NONE,
                    success = false,
                    errorMessage = "AI service is temporarily unavailable. Please try again.",
                    metadata = mapOf("backend" to true, "status" to responseCode),
                )
            }

            val responseBody = BufferedReader(InputStreamReader(connection.inputStream, Charsets.UTF_8)).use { it.readText() }
            val root = JSONObject(responseBody)
            val success = root.optBoolean("success", false)
            val data = root.optJSONObject("data") ?: root.optJSONObject("response")
            val text = data?.optString("text", "") ?: root.optString("text", "")

            if (!success || text.isBlank()) {
                return@withContext AIResponse(
                    text = "",
                    provider = AIProviderType.NONE,
                    success = false,
                    errorMessage = "AI service is temporarily unavailable. Please try again.",
                    metadata = mapOf("backend" to true, "status" to "invalid_response"),
                )
            }

            AIResponse(
                text = text,
                provider = AIProviderType.NONE,
                success = true,
                fallbackUsed = false,
                metadata = mapOf("backend" to true, "request_id" to root.optString("requestId", "")),
            )
        } catch (_: Throwable) {
            AIResponse(
                text = "",
                provider = AIProviderType.NONE,
                success = false,
                errorMessage = "AI service is temporarily unavailable. Please try again.",
                metadata = mapOf("backend" to true, "reason" to "network_error"),
            )
        }
    }

    override fun streamChat(request: AIRequest): Flow<AIResponseChunk> = flow {
        val response = chat(request)
        if (response.success && response.text.isNotBlank()) {
            emit(AIResponseChunk(text = response.text, provider = AIProviderType.NONE, isFinal = true, chunkIndex = 0))
        } else {
            emit(AIResponseChunk(text = "", provider = AIProviderType.NONE, isFinal = true, chunkIndex = 0))
        }
    }

    override suspend fun healthCheck(): Boolean = withContext(Dispatchers.IO) {
        val config = backendConfigProvider.provide()
        val baseUrl = config.baseUrl.trimEnd('/').ifBlank { "https://api.priya.app" }
        if (!baseUrl.startsWith("https://")) return@withContext false
        return@withContext runCatching {
            val connection = URL("$baseUrl/api/v1/health").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = TimeUnit.SECONDS.toMillis(10).toInt()
            connection.readTimeout = TimeUnit.SECONDS.toMillis(10).toInt()
            connection.responseCode in 200..299
        }.getOrDefault(false)
    }
}
