package com.priya.app.domain.model

import java.util.UUID

enum class MemoryType {
    PREFERENCE,
    ROUTINE,
    STUDY,
    GENERAL,
    SYSTEM,
}

data class MemoryRecord(
    val id: String = UUID.randomUUID().toString(),
    val type: MemoryType = MemoryType.GENERAL,
    val key: String = "",
    val value: String = "",
    val importance: Double = 0.5,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = createdAt,
)
