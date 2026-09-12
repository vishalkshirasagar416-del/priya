package com.priya.app.services

interface AudioService {
    suspend fun startListening(): Result<Unit>
    suspend fun stopListening(): Result<Unit>
    suspend fun speakText(text: String): Result<Unit>
}
