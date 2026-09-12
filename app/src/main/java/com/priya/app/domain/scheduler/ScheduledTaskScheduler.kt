package com.priya.app.domain.scheduler

interface ScheduledTaskScheduler {
    suspend fun schedule(task: ScheduledTask): Result<ScheduledTask>
    suspend fun cancel(taskId: String): Result<Unit>
    suspend fun cancelMatching(type: TaskType, title: String): Result<Unit>
    suspend fun listPending(): List<ScheduledTask>
    suspend fun restoreAfterReboot(): Result<Unit>
}
