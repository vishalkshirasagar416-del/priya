package com.priya.app.domain.repository

import com.priya.app.domain.model.MemoryRecord
import com.priya.app.domain.model.MemoryType
import kotlinx.coroutines.flow.Flow

interface MemoryRepository {
    fun observeMemories(): Flow<List<MemoryRecord>>
    suspend fun getMemories(): List<MemoryRecord>
    suspend fun addMemory(memory: MemoryRecord): MemoryRecord
    suspend fun deleteMemory(id: String)
    suspend fun clearAll()
}

interface LocalMemoryRepository : MemoryRepository {
    suspend fun remember(type: MemoryType, key: String, value: String, importance: Double = 0.5): MemoryRecord
    suspend fun rememberPreference(key: String, value: String): MemoryRecord
}

interface CloudMemoryRepository {
    suspend fun syncMemories(memories: List<MemoryRecord>): Result<Unit>
    suspend fun fetchMemories(): Result<List<MemoryRecord>>
}
