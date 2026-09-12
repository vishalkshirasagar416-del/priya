package com.priya.app.domain.repository

import com.priya.app.domain.model.Conversation
import com.priya.app.domain.model.LanguagePreference
import com.priya.app.domain.model.Message

interface ConversationRepository {
    suspend fun getConversation(id: String): Conversation?
    suspend fun saveConversation(conversation: Conversation)
    suspend fun addMessage(conversationId: String, message: Message)
    suspend fun updateLanguagePreference(conversationId: String, languagePreference: LanguagePreference)
    fun detectLanguage(input: String): LanguagePreference
}
