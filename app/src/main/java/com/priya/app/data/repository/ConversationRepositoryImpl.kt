package com.priya.app.data.repository

import com.priya.app.domain.model.Conversation
import com.priya.app.domain.model.LanguagePreference
import com.priya.app.domain.model.Message
import com.priya.app.domain.repository.ConversationRepository
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryConversationRepository @Inject constructor() : ConversationRepository {
    private val conversations = ConcurrentHashMap<String, Conversation>()

    override suspend fun getConversation(id: String): Conversation? = conversations[id]

    override suspend fun saveConversation(conversation: Conversation) {
        conversations[conversation.id] = conversation
    }

    override suspend fun addMessage(conversationId: String, message: Message) {
        val conversation = conversations[conversationId]
            ?: Conversation(id = conversationId)
        conversation.messages.add(message)
        conversations[conversationId] = conversation
    }

    override suspend fun updateLanguagePreference(conversationId: String, languagePreference: LanguagePreference) {
        val conversation = conversations[conversationId] ?: return
        conversations[conversationId] = conversation.copy(languagePreference = languagePreference)
    }

    override fun detectLanguage(input: String): LanguagePreference {
        val normalized = input.trim()
        if (normalized.isEmpty()) return LanguagePreference.AUTO

        val hasKannada = normalized.any { ch -> ch.code in 0x0C80..0x0CFF }
        val hasHindi = normalized.any { ch -> ch.code in 0x0900..0x097F }
        val hasEnglish = normalized.any { ch -> ch.isLetter() && ch.lowercaseChar() in 'a'..'z' }

        if (hasKannada && !hasEnglish) return LanguagePreference.KANNADA
        if (hasHindi && !hasEnglish) return LanguagePreference.HINDI
        if (hasEnglish && (normalized.contains("namaste", ignoreCase = true) || normalized.contains("yaar", ignoreCase = true) || normalized.contains("bro", ignoreCase = true) || normalized.contains("buddy", ignoreCase = true))) {
            return LanguagePreference.HINGLISH
        }
        if (hasKannada && hasEnglish) return LanguagePreference.KANNADA
        if (hasHindi && hasEnglish) return LanguagePreference.HINGLISH
        if (hasEnglish) return LanguagePreference.ENGLISH
        return LanguagePreference.AUTO
    }
}
