package com.priya.app.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.priya.app.domain.scheduler.ScheduledTaskStatus
import com.priya.app.domain.scheduler.TaskType

@Entity(tableName = "scheduled_tasks")
data class ScheduledTaskEntity(
    @PrimaryKey val id: String,
    val type: String,
    val title: String,
    val description: String = "",
    @ColumnInfo(name = "scheduled_at_epoch_millis") val scheduledAtEpochMillis: Long,
    val status: String = ScheduledTaskStatus.PENDING.name,
    @ColumnInfo(name = "created_at_epoch_millis") val createdAtEpochMillis: Long,
    @ColumnInfo(name = "is_exact") val isExact: Boolean = false,
    @ColumnInfo(name = "is_recurring") val isRecurring: Boolean = false,
) {
    fun toDomain(): com.priya.app.domain.scheduler.ScheduledTask = com.priya.app.domain.scheduler.ScheduledTask(
        id = id,
        type = TaskType.valueOf(type),
        title = title,
        description = description,
        scheduledAtEpochMillis = scheduledAtEpochMillis,
        status = ScheduledTaskStatus.valueOf(status),
        createdAtEpochMillis = createdAtEpochMillis,
        isExact = isExact,
        isRecurring = isRecurring,
    )

    companion object {
        fun fromDomain(task: com.priya.app.domain.scheduler.ScheduledTask): ScheduledTaskEntity = ScheduledTaskEntity(
            id = task.id,
            type = task.type.name,
            title = task.title,
            description = task.description,
            scheduledAtEpochMillis = task.scheduledAtEpochMillis,
            status = task.status.name,
            createdAtEpochMillis = task.createdAtEpochMillis,
            isExact = task.isExact,
            isRecurring = task.isRecurring,
        )
    }
}
