package com.priya.app.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.priya.app.domain.model.MemoryType
import java.util.UUID

class MemoryTypeConverters {
    @TypeConverter
    fun fromMemoryType(type: MemoryType?): String? = type?.name

    @TypeConverter
    fun toMemoryType(value: String?): MemoryType? = value?.let { MemoryType.valueOf(it) }
}

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "display_name") val displayName: String = "",
    @ColumnInfo(name = "preferred_language") val preferredLanguage: String = "",
    @ColumnInfo(name = "voice_id") val voiceId: String = "",
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at") val updatedAt: Long = createdAt,
)

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at") val updatedAt: Long = createdAt,
)

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "conversation_id") val conversationId: String = "",
    val role: String = "USER",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis(),
)

@Entity(tableName = "memories")
data class MemoryEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val type: String = MemoryType.GENERAL.name,
    @ColumnInfo(name = "memory_key") val key: String = "",
    val value: String = "",
    val importance: Double = 0.5,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at") val updatedAt: Long = createdAt,
)

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    @ColumnInfo(name = "scheduled_at") val scheduledAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "repeat_rule") val repeatRule: String = "NONE",
    val enabled: Boolean = true,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "user_settings")
data class UserSettingsEntity(
    @PrimaryKey val id: String = "primary",
    val theme: String = "SYSTEM",
    val language: String = "en",
    @ColumnInfo(name = "voice_enabled") val voiceEnabled: Boolean = true,
    @ColumnInfo(name = "wake_word_enabled") val wakeWordEnabled: Boolean = true,
    @ColumnInfo(name = "ai_provider") val aiProvider: String = "gemini",
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at") val updatedAt: Long = createdAt,
)
