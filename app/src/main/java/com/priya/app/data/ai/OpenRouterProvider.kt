package com.priya.app.data.ai

import android.util.Log
import com.priya.app.domain.repository.AIConfigRepository
import com.priya.app.domain.ai.AIProvider
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenRouterProvider @Inject constructor(
    private val configRepository: AIConfigRepository,
) : AIProvider {
    override val providerType: AIProviderType = AIProviderType.OPENROUTER

    override suspend fun generateText(request: AIRequest): AIResponse = withContext(Dispatchers.IO) {
        val apiKey = configRepository.getConfig().openRouterApiKey
        if (apiKey.isBlank()) {
            return@withContext AIResponse(
                text = "",
                provider = providerType,
                success = false,
                errorMessage = "OpenRouter API key is not configured. Open Settings -> AI provider to add your key.",
                metadata = mapOf("configured" to false),
            )
        }

        return@withContext try {
            val endpoint = "https://openrouter.ai/api/v1/chat/completions"
            val payload = JSONObject().apply {
                put("model", "openai/gpt-oss-20b:free")
                put("messages", JSONArray().apply {
                    put(JSONObject().put("role", "system").put("content", request.systemPrompt))
                    put(JSONObject().put("role", "user").put("content", request.prompt))
                })
                put("temperature", 0.8)
            }

            val responseText = postJson(endpoint, payload.toString(), apiKey)
            val extractedText = extractOpenRouterText(responseText)
            if (extractedText.isBlank()) {
                AIResponse(
                    text = "",
                    provider = providerType,
                    success = false,
                    errorMessage = "OpenRouter returned an empty response.",
                    metadata = mapOf("configured" to true),
                )
            } else {
                AIResponse(
                    text = extractedText,
                    provider = providerType,
                    success = true,
                    fallbackUsed = true,
                    metadata = mapOf("configured" to true, "language" to request.languagePreference.name),
                )
            }
        } catch (throwable: Throwable) {
            if (isRecoverableFailure(throwable)) {
                throw throwable
            }
            AIResponse(
                text = "",
                provider = providerType,
                success = false,
                errorMessage = "OpenRouter request failed. Please check your configuration or network connection.",
                metadata = mapOf("configured" to true, "recoverable" to false),
            )
        }
    }

    override fun streamText(request: AIRequest): Flow<AIResponseChunk> = flow {
        val response = generateText(request)
        if (response.success && response.text.isNotBlank()) {
            val chunks = chunkText(response.text)
            chunks.forEachIndexed { index, chunk ->
                emit(AIResponseChunk(chunk, providerType, isFinal = index == chunks.lastIndex, chunkIndex = index))
            }
        } else {
            emit(AIResponseChunk("", providerType, isFinal = true, chunkIndex = 0))
        }
    }

    override fun isConfigured(): Boolean = configRepository.getConfig().openRouterApiKey.isNotBlank()

    override fun isRecoverableFailure(error: Throwable): Boolean {
        val message = error.message.orEmpty().lowercase()
        return message.contains("timeout") ||
            message.contains("429") ||
            message.contains("rate limit") ||
            message.contains("temporarily unavailable") ||
            message.contains("503") ||
            message.contains("500") ||
            message.contains("connection") ||
            message.contains("unavailable")
    }

    private fun chunkText(text: String): List<String> {
        if (text.length <= 22) return listOf(text)
        return text.chunked(22)
    }

    private fun postJson(url: String, payload: String, apiKey: String): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Authorization", "Bearer $apiKey")
        connection.setRequestProperty("HTTP-Referer", "https://priya.app")
        connection.setRequestProperty("X-Title", "Priya")
        connection.connectTimeout = TimeUnit.SECONDS.toMillis(20).toInt()
        connection.readTimeout = TimeUnit.SECONDS.toMillis(20).toInt()
        connection.doOutput = true

        BufferedWriter(OutputStreamWriter(connection.outputStream, Charsets.UTF_8)).use { writer ->
            writer.write(payload)
            writer.flush()
        }

        val responseCode = connection.responseCode
        val reader = if (responseCode in 200..299) {
            BufferedReader(InputStreamReader(connection.inputStream, Charsets.UTF_8))
        } else {
            BufferedReader(InputStreamReader(connection.errorStream ?: connection.inputStream, Charsets.UTF_8))
        }

        return reader.use { it.readText() }
    }

    private fun extractOpenRouterText(json: String): String {
        return try {
            val root = JSONObject(json)
            val choices = root.optJSONArray("choices") ?: return ""
            for (index in 0 until choices.length()) {
                val choice = choices.optJSONObject(index) ?: continue
                val message = choice.optJSONObject("message") ?: continue
                val content = message.opt("content")
                if (content is String && content.isNotBlank()) return content.trim()
                if (content is JSONArray) {
                    val builder = StringBuilder()
                    for (partIndex in 0 until content.length()) {
                        val item = content.optJSONObject(partIndex) ?: continue
                        val text = item.optString("text", "")
                        if (text.isNotBlank()) builder.append(text)
                    }
                    val combined = builder.toString().trim()
                    if (combined.isNotBlank()) return combined
                }
            }
            ""
        } catch (throwable: Throwable) {
            Log.w("PriyaAI", "Failed to parse OpenRouter response: ${throwable.message}")
            ""
        }
    }
}
