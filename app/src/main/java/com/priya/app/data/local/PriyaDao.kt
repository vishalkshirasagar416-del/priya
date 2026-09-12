package com.priya.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PriyaDao {
    @Query("SELECT * FROM memories ORDER BY created_at DESC")
    fun observeMemories(): Flow<List<MemoryEntity>>

    @Query("SELECT * FROM memories ORDER BY created_at DESC")
    suspend fun getMemories(): List<MemoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemory(memory: MemoryEntity)

    @Query("DELETE FROM memories WHERE id = :id")
    suspend fun deleteMemory(id: String)

    @Query("DELETE FROM memories")
    suspend fun clearAllMemories()

    @Query("SELECT * FROM scheduled_tasks WHERE status = 'PENDING' ORDER BY scheduled_at_epoch_millis ASC")
    suspend fun getPendingScheduledTasks(): List<ScheduledTaskEntity>

    @Query("SELECT * FROM scheduled_tasks WHERE id = :id LIMIT 1")
    suspend fun getScheduledTask(id: String): ScheduledTaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertScheduledTask(task: ScheduledTaskEntity)

    @Query("UPDATE scheduled_tasks SET status = :status WHERE id = :id")
    suspend fun updateScheduledTaskStatus(id: String, status: String)

    @Query("DELETE FROM scheduled_tasks WHERE id = :id")
    suspend fun deleteScheduledTask(id: String)

    @Query("SELECT * FROM scheduled_tasks ORDER BY scheduled_at_epoch_millis ASC")
    fun observeScheduledTasks(): Flow<List<ScheduledTaskEntity>>
}
