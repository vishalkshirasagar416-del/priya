package com.priya.app.data.tools

import com.priya.app.domain.tools.AssistantTool
import com.priya.app.domain.tools.ToolRegistry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultToolRegistry @Inject constructor(
    private val registeredTools: Set<@JvmSuppressWildcards AssistantTool>,
) : ToolRegistry {
    override val tools: List<AssistantTool> = registeredTools.toList()

    override fun getTool(name: String): AssistantTool? = registeredTools.firstOrNull { it.name == name }

    override fun hasTool(name: String): Boolean = registeredTools.any { it.name == name }
}
