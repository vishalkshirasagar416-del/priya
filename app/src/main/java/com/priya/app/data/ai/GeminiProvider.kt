package com.priya.app.data.ai

import android.util.Log
import com.priya.app.BuildConfig
import com.priya.app.domain.ai.AIProvider
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.ai.AIResponse
import com.priya.app.domain.ai.AIResponseChunk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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
class GeminiProvider @Inject constructor() : AIProvider {
    override val providerType: AIProviderType = AIProviderType.GEMINI

    override suspend fun generateText(request: AIRequest): AIResponse = withContext(Dispatchers.IO) {
        if (!isConfigured()) {
            return@withContext AIResponse(
                text = "",
                provider = providerType,
                success = false,
                errorMessage = "Gemini is not configured. Add GEMINI_API_KEY in local.properties during development.",
                metadata = mapOf("configured" to false),
            )
        }

        return@withContext try {
            val endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${BuildConfig.GEMINI_API_KEY}"
            val payload = JSONObject().apply {
                put("contents", listOf(
                    JSONObject().put(
                        "parts",
                        listOf(JSONObject().put("text", "${request.systemPrompt}\n\nUser: ${request.prompt}"))
                    )
                ))
                put("generationConfig", JSONObject().put("temperature", 0.8))
            }

            val responseText = postJson(endpoint, payload.toString())
            val extractedText = extractGeminiText(responseText)
            if (extractedText.isBlank()) {
                AIResponse(
                    text = "",
                    provider = providerType,
                    success = false,
                    errorMessage = "Gemini returned an empty response.",
                    metadata = mapOf("configured" to true),
                )
            } else {
                AIResponse(
                    text = extractedText,
                    provider = providerType,
                    success = true,
                    fallbackUsed = false,
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
                errorMessage = throwable.message ?: "Gemini request failed.",
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

    override fun isConfigured(): Boolean = BuildConfig.GEMINI_API_KEY.isNotBlank()

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

    private fun postJson(url: String, payload: String): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
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

    private fun extractGeminiText(json: String): String {
        return try {
            val root = JSONObject(json)
            val candidates = root.optJSONArray("candidates") ?: return ""
            for (index in 0 until candidates.length()) {
                val candidate = candidates.optJSONObject(index) ?: continue
                val content = candidate.optJSONObject("content") ?: continue
                val parts = content.optJSONArray("parts") ?: continue
                for (partIndex in 0 until parts.length()) {
                    val part = parts.optJSONObject(partIndex) ?: continue
                    val text = part.optString("text", "").trim()
                    if (text.isNotBlank()) return text
                }
            }
            ""
        } catch (throwable: Throwable) {
            Log.w("PriyaAI", "Failed to parse Gemini response: ${throwable.message}")
            ""
        }
    }
}
