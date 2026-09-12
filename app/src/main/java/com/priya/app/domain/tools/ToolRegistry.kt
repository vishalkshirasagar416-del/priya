package com.priya.app.domain.tools

interface ToolRegistry {
    val tools: List<AssistantTool>
    fun getTool(name: String): AssistantTool?
    fun hasTool(name: String): Boolean
}
