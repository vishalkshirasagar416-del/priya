package com.priya.app.domain.tools

data class ToolParameter(
    val name: String,
    val type: String,
    val required: Boolean = false,
    val description: String = "",
)

data class ToolResult(
    val success: Boolean,
    val message: String? = null,
    val error: String? = null,
    val data: Map<String, Any?> = emptyMap(),
)

interface AssistantTool {
    val name: String
    val description: String
    val parameters: List<ToolParameter>
    suspend fun execute(arguments: Map<String, String>): ToolResult
}
