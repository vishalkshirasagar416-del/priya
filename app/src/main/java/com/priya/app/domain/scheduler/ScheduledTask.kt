package com.priya.app.domain.scheduler

import java.util.UUID

enum class TaskType {
    REMINDER,
    ALARM,
    TIMER,
    NOTIFICATION,
}

enum class ScheduledTaskStatus {
    PENDING,
    DISPATCHED,
    CANCELLED,
    MISSED,
}

data class ScheduledTask(
    val id: String = UUID.randomUUID().toString(),
    val type: TaskType,
    val title: String,
    val description: String = "",
    val scheduledAtEpochMillis: Long,
    val status: ScheduledTaskStatus = ScheduledTaskStatus.PENDING,
    val createdAtEpochMillis: Long = System.currentTimeMillis(),
    val isExact: Boolean = false,
    val isRecurring: Boolean = false,
)

object ScheduledTaskPolicy {
    private const val DUPLICATE_WINDOW_MS = 60_000L

    fun isDuplicate(existing: ScheduledTask, candidate: ScheduledTask): Boolean {
        if (existing.type != candidate.type) return false
        if (existing.status != ScheduledTaskStatus.PENDING || candidate.status != ScheduledTaskStatus.PENDING) return false
        if (!existing.title.equals(candidate.title, ignoreCase = true)) return false
        val timeDelta = kotlin.math.abs(existing.scheduledAtEpochMillis - candidate.scheduledAtEpochMillis)
        return timeDelta <= DUPLICATE_WINDOW_MS
    }
}
