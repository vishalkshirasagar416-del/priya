package com.priya.app.domain.model

import java.util.UUID

enum class Role {
    USER,
    ASSISTANT,
    SYSTEM,
    TOOL,
}

enum class LanguagePreference {
    AUTO,
    ENGLISH,
    HINDI,
    KANNADA,
    HINGLISH,
}

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val conversationId: String,
    val role: Role,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val metadata: Map<String, Any?> = emptyMap(),
)

data class Conversation(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "Priya chat",
    val languagePreference: LanguagePreference = LanguagePreference.AUTO,
    val messages: MutableList<Message> = mutableListOf(),
    val createdAt: Long = System.currentTimeMillis(),
)
