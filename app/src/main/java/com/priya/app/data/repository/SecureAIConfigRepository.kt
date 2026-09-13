package com.priya.app.data.repository

import android.content.Context
import com.priya.app.data.security.KeystoreKeyProvider
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.domain.repository.AIConfig
import com.priya.app.domain.repository.AIConfigRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureAIConfigRepository @Inject constructor(
    @ApplicationContext context: Context,
) : AIConfigRepository {
    private val preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    private val cipher = KeystoreKeyProvider(context, KEY_ALIAS)

    override fun getConfig(): AIConfig {
        val encrypted = preferences.getString(CONFIG_KEY, null) ?: return AIConfig()
        return runCatching {
            val json = JSONObject(cipher.decrypt(encrypted))
            AIConfig(
                geminiApiKey = json.optString("gemini", ""),
                openRouterApiKey = json.optString("openRouter", ""),
                elevenLabsApiKey = json.optString("elevenLabs", ""),
                primaryProvider = json.optString("primary", AIProviderType.GEMINI.name)
                    .let { runCatching { AIProviderType.valueOf(it) }.getOrDefault(AIProviderType.GEMINI) },
                setupCompleted = json.optBoolean("setupCompleted", false),
            )
        }.getOrDefault(AIConfig())
    }

    override fun saveConfig(config: AIConfig) {
        val normalized = config.copy(
            geminiApiKey = config.geminiApiKey.trim(),
            openRouterApiKey = config.openRouterApiKey.trim(),
            elevenLabsApiKey = config.elevenLabsApiKey.trim(),
            setupCompleted = config.setupCompleted ||
                config.geminiApiKey.isNotBlank() || config.openRouterApiKey.isNotBlank(),
        )
        val json = JSONObject().apply {
            put("gemini", normalized.geminiApiKey)
            put("openRouter", normalized.openRouterApiKey)
            put("elevenLabs", normalized.elevenLabsApiKey)
            put("primary", normalized.primaryProvider.name)
            put("setupCompleted", normalized.setupCompleted)
        }
        preferences.edit().putString(CONFIG_KEY, cipher.encrypt(json.toString())).apply()
    }

    override fun clearProvider(provider: AIProviderType) {
        val current = getConfig()
        saveConfig(
            when (provider) {
                AIProviderType.GEMINI -> current.copy(geminiApiKey = "")
                AIProviderType.OPENROUTER -> current.copy(openRouterApiKey = "")
                AIProviderType.NONE -> current.copy(elevenLabsApiKey = "")
            }
        )
    }

    private companion object {
        const val PREFERENCES = "priya_secure_ai_config"
        const val CONFIG_KEY = "encrypted_config"
        const val KEY_ALIAS = "priya_ai_config_key"
    }
}