package com.priya.app.domain.memory

enum class MemoryCommandType {
    STORE,
    QUERY,
    DELETE,
    CLEAR,
    IGNORE,
}

data class MemoryCommandResult(
    val commandType: MemoryCommandType,
    val key: String,
    val value: String = "",
    val originalText: String = "",
)

class MemoryCommandInterpreter {
    fun interpret(input: String): MemoryCommandResult {
        val text = input.trim()
        if (text.isBlank()) {
            return MemoryCommandResult(MemoryCommandType.IGNORE, "", "", text)
        }

        when {
            text.contains("clear my memories", ignoreCase = true) ->
                return MemoryCommandResult(MemoryCommandType.CLEAR, "all", "", text)

            text.contains("what do you remember about me", ignoreCase = true) ||
                text.contains("what do you remember", ignoreCase = true) ->
                return MemoryCommandResult(MemoryCommandType.QUERY, "profile", "", text)

            text.contains("remember that i prefer", ignoreCase = true) -> {
                val preference = text.substringAfter("prefer", missingDelimiterValue = "")
                    .trim()
                    .removeSuffix(".")
                    .removeSuffix("!")
                val language = preference.replaceFirstChar { it.uppercase() }
                return MemoryCommandResult(
                    commandType = MemoryCommandType.STORE,
                    key = "preferred_language",
                    value = language.ifBlank { "Kannada" },
                    originalText = text,
                )
            }

            text.contains("forget that", ignoreCase = true) ||
                text.contains("don't remember this", ignoreCase = true) ||
                text.contains("do not remember this", ignoreCase = true) ->
                return MemoryCommandResult(MemoryCommandType.DELETE, "latest", "", text)
        }

        return MemoryCommandResult(MemoryCommandType.IGNORE, "", "", text)
    }
}
