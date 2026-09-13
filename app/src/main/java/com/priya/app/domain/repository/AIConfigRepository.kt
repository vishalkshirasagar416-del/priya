package com.priya.app.domain.repository

import com.priya.app.domain.ai.AIProviderType

data class AIConfig(
    val geminiApiKey: String = "",
    val openRouterApiKey: String = "",
    val elevenLabsApiKey: String = "",
    val primaryProvider: AIProviderType = AIProviderType.GEMINI,
    val setupCompleted: Boolean = false,
)

interface AIConfigRepository {
    fun getConfig(): AIConfig
    fun saveConfig(config: AIConfig)
    fun clearProvider(provider: AIProviderType)
}