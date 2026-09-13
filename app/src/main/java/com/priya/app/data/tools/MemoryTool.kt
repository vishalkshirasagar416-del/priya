package com.priya.app.data.tools

import com.priya.app.domain.memory.MemoryCommandInterpreter
import com.priya.app.domain.memory.MemoryCommandType
import com.priya.app.domain.repository.LocalMemoryRepository
import com.priya.app.domain.tools.AssistantTool
import com.priya.app.domain.tools.ToolParameter
import com.priya.app.domain.tools.ToolResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryTool @Inject constructor(
    private val memoryRepository: LocalMemoryRepository,
) : AssistantTool {
    private val interpreter = MemoryCommandInterpreter()

    override val name: String = "memory_query"
    override val description: String = "Store, retrieve, or clear safe personal memories."
    override val parameters: List<ToolParameter> = listOf(
        ToolParameter("text", "string", true, "The user's memory instruction"),
    )

    override suspend fun execute(arguments: Map<String, String>): ToolResult {
        val text = arguments["text"]?.trim().orEmpty()
        if (text.isBlank()) return ToolResult(false, error = "Memory instruction is required.")

        return runCatching {
            val command = interpreter.interpret(text)
            when (command.commandType) {
                    MemoryCommandType.STORE -> {
                        memoryRepository.rememberPreference(command.key, command.value)
                        ToolResult(true, "I will remember that your preferred language is ${command.value}.")
                    }
                    MemoryCommandType.QUERY -> {
                        val memories = memoryRepository.getMemories()
                        val message = memories.take(5).joinToString("; ") { "${it.key}: ${it.value}" }
                        ToolResult(true, if (message.isBlank()) "I do not have any saved memories yet." else message)
                    }
                    MemoryCommandType.CLEAR -> {
                        memoryRepository.clearAll()
                        ToolResult(true, "I cleared your saved memories.")
                    }
                    MemoryCommandType.DELETE -> {
                        val latest = memoryRepository.getMemories().maxByOrNull { it.updatedAt }
                        if (latest == null) {
                            ToolResult(true, "There are no saved memories to forget.")
                        } else {
                            memoryRepository.deleteMemory(latest.id)
                            ToolResult(true, "I forgot your latest saved memory.")
                        }
                    }
                    MemoryCommandType.IGNORE -> ToolResult(false, error = "I could not understand the memory request.")
            }
        }.getOrElse { throwable ->
            ToolResult(false, error = throwable.message ?: "Memory operation failed.")
        }
    }
}