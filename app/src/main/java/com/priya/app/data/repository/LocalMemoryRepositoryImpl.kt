package com.priya.app.data.repository

import android.content.Context
import com.priya.app.data.local.MemoryEntity
import com.priya.app.data.local.PriyaDao
import com.priya.app.data.security.KeystoreKeyProvider
import com.priya.app.domain.model.MemoryRecord
import com.priya.app.domain.model.MemoryType
import com.priya.app.domain.repository.LocalMemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMemoryRepositoryImpl @Inject constructor(
    private val dao: com.priya.app.data.local.PriyaDao,
    context: Context,
) : LocalMemoryRepository {
    private val secureValueStore = KeystoreKeyProvider(context.applicationContext)

    override fun observeMemories(): Flow<List<MemoryRecord>> {
        return dao.observeMemories().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getMemories(): List<MemoryRecord> {
        return dao.getMemories().map { it.toDomain() }
    }

    override suspend fun addMemory(memory: MemoryRecord): MemoryRecord {
        val clean = sanitize(memory)
        val entity = MemoryEntity(
            id = clean.id.ifBlank { UUID.randomUUID().toString() },
            type = clean.type.name,
            key = clean.key,
            value = secureValueStore.encrypt(clean.value),
            importance = clean.importance,
            createdAt = clean.createdAt,
            updatedAt = System.currentTimeMillis(),
        )
        dao.insertMemory(entity)
        return clean.copy(updatedAt = entity.updatedAt)
    }

    override suspend fun deleteMemory(id: String) {
        dao.deleteMemory(id)
    }

    override suspend fun clearAll() {
        dao.clearAllMemories()
    }

    override suspend fun remember(type: MemoryType, key: String, value: String, importance: Double): MemoryRecord {
        val memory = sanitize(
            MemoryRecord(
                type = type,
                key = key,
                value = value,
                importance = importance,
            )
        )
        return addMemory(memory)
    }

    override suspend fun rememberPreference(key: String, value: String): MemoryRecord {
        return remember(MemoryType.PREFERENCE, key, value, importance = 0.9)
    }

    private fun sanitize(memory: MemoryRecord): MemoryRecord {
        val cleanKey = memory.key.trim()
        val cleanValue = memory.value.trim()
        require(cleanKey.isNotBlank()) { "Memory key cannot be blank." }
        require(cleanValue.isNotBlank()) { "Memory value cannot be blank." }
        require(!containsSensitiveData(cleanKey) && !containsSensitiveData(cleanValue)) {
            "Sensitive data is not allowed in local memories."
        }
        return memory.copy(
            key = cleanKey,
            value = cleanValue,
            importance = memory.importance.coerceIn(0.0, 1.0),
        )
    }

    companion object {
        fun containsSensitiveData(value: String): Boolean {
            val normalized = value.lowercase()
            val patterns = listOf(
                "password",
                "api key",
                "apikey",
                "access token",
                "refresh token",
                "bearer ",
                "authorization",
                "jwt",
                "oauth",
                "secret",
                "bank",
                "credit card",
                "ssn",
                "otp",
                "cookie",
                "sessionid",
                "client_secret",
                "private key",
            )
            return patterns.any { normalized.contains(it) }
        }
    }

    private fun MemoryEntity.toDomain(): MemoryRecord {
        return MemoryRecord(
            id = id,
            type = MemoryType.valueOf(type),
            key = key,
            value = secureValueStore.decrypt(value),
            importance = importance,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
