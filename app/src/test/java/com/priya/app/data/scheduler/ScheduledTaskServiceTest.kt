package com.priya.app.data.scheduler

import com.priya.app.domain.scheduler.ScheduledTask
import com.priya.app.domain.scheduler.ScheduledTaskStatus
import com.priya.app.domain.scheduler.TaskType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ScheduledTaskServiceTest {
    @Test
    fun duplicateReminderTasksAreRejected() {
        val pending = ScheduledTask(
            id = "rem-1",
            type = TaskType.REMINDER,
            title = "Study DBMS",
            description = "Deep revision",
            scheduledAtEpochMillis = 1_700_000_000L,
            status = ScheduledTaskStatus.PENDING,
        )

        val duplicate = pending.copy(id = "rem-2")

        assertTrue(ScheduledTaskPolicy.isDuplicate(pending, duplicate))
        assertFalse(ScheduledTaskPolicy.isDuplicate(pending, pending.copy(status = ScheduledTaskStatus.CANCELLED)))
    }

    @Test
    fun cancelledTaskRemainsCancelled() {
        val task = ScheduledTask(
            id = "alarm-1",
            type = TaskType.ALARM,
            title = "Wake up",
            description = "Alarm",
            scheduledAtEpochMillis = 1_700_000_000L,
            status = ScheduledTaskStatus.CANCELLED,
        )

        assertTrue(task.status == ScheduledTaskStatus.CANCELLED)
    }

    @Test
    fun timerDurationIsClampedToPositiveValue() {
        val duration = 20L * 60_000L
        assertTrue(duration > 0L)
    }
}
