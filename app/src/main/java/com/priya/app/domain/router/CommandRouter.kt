package com.priya.app.domain.router

import com.priya.app.data.tools.ToolArgumentValidator
import com.priya.app.domain.tools.AssistantTool
import com.priya.app.domain.tools.ToolParameter
import com.priya.app.domain.tools.ToolRegistry
import com.priya.app.domain.tools.ToolResult
import kotlin.math.max

enum class CommandIntent {
    CONVERSATION,
    ANDROID_ACTION,
    WEB_SEARCH,
    MEMORY,
    REMINDER,
    ALARM,
    CALENDAR,
    CALL,
    MESSAGE,
    MUSIC,
    STUDY,
    CODING,
    WEATHER,
    SYSTEM_STATUS,
}

data class ToolRequest(
    val tool: String,
    val arguments: Map<String, String> = emptyMap(),
)

data class LocalIntentDecision(
    val intent: CommandIntent,
    val requiresLLM: Boolean = false,
    val toolRequest: ToolRequest? = null,
    val confidence: Float = 1.0f,
)

data class RouteResult(
    val intent: CommandIntent,
    val requiresLLM: Boolean,
    val toolRequest: ToolRequest? = null,
    val toolResult: ToolResult? = null,
    val finalResponse: String? = null,
)

interface LLMToolGenerator {
    suspend fun generateToolRequest(prompt: String): ToolRequest?
}

class LocalIntentClassifier {
    fun classify(input: String): LocalIntentDecision {
        val text = input.trim()
        if (text.isBlank()) {
            return LocalIntentDecision(CommandIntent.CONVERSATION, requiresLLM = true, confidence = 0.0f)
        }

        val normalized = text.lowercase()

        if (hasAny(normalized, "what time", "current time", "time is it", "what is the time")) {
            return LocalIntentDecision(
                intent = CommandIntent.SYSTEM_STATUS,
                requiresLLM = false,
                toolRequest = ToolRequest("get_time"),
                confidence = 0.98f,
            )
        }

        if (hasAny(normalized, "battery", "power level", "how much battery", "what's my battery", "battery status")) {
            return LocalIntentDecision(
                intent = CommandIntent.SYSTEM_STATUS,
                requiresLLM = false,
                toolRequest = ToolRequest("get_battery_status"),
                confidence = 0.97f,
            )
        }

        if (hasAny(normalized, "date", "day is it", "today's date")) {
            return LocalIntentDecision(
                intent = CommandIntent.SYSTEM_STATUS,
                requiresLLM = false,
                toolRequest = ToolRequest("get_date"),
                confidence = 0.95f,
            )
        }

        if (hasAny(normalized, "network", "signal", "wifi", "mobile data")) {
            return LocalIntentDecision(
                intent = CommandIntent.SYSTEM_STATUS,
                requiresLLM = false,
                toolRequest = ToolRequest("get_network_status"),
                confidence = 0.94f,
            )
        }

        if (hasAny(normalized, "storage", "memory", "disk space", "available storage")) {
            return LocalIntentDecision(
                intent = CommandIntent.SYSTEM_STATUS,
                requiresLLM = false,
                toolRequest = ToolRequest("get_storage_status"),
                confidence = 0.93f,
            )
        }

        if (hasAny(normalized, "device info", "what phone", "what model", "tell me about this device")) {
            return LocalIntentDecision(
                intent = CommandIntent.SYSTEM_STATUS,
                requiresLLM = false,
                toolRequest = ToolRequest("get_device_info"),
                confidence = 0.92f,
            )
        }

        if (hasAny(normalized, "search for", "find the latest", "latest ai news", "look up", "search web", "google")) {
            val query = extractSearchQuery(text)
            return LocalIntentDecision(
                intent = CommandIntent.WEB_SEARCH,
                requiresLLM = false,
                toolRequest = ToolRequest("search_web", mapOf("query" to query)),
                confidence = 0.91f,
            )
        }

        if (hasAny(normalized, "open youtube", "launch youtube", "start youtube") ||
            (normalized.contains("open ") && extractAppName(text) != null)
        ) {
            val appName = extractAppName(text) ?: "youtube"
            return LocalIntentDecision(
                intent = CommandIntent.ANDROID_ACTION,
                requiresLLM = false,
                toolRequest = ToolRequest(
                    "open_app",
                    mapOf("package_name" to inferPackageName(appName)),
                ),
                confidence = 0.9f,
            )
        }

        if (hasAny(normalized, "open settings", "go to settings", "settings app")) {
            return LocalIntentDecision(
                intent = CommandIntent.ANDROID_ACTION,
                requiresLLM = false,
                toolRequest = ToolRequest("open_settings"),
                confidence = 0.95f,
            )
        }

        if (hasAny(normalized, "call ", "phone ", "dial ", "make a call", "call my")) {
            return LocalIntentDecision(
                intent = CommandIntent.CALL,
                requiresLLM = false,
                toolRequest = ToolRequest("make_call", mapOf("contact_name" to extractCallTarget(text))),
                confidence = 0.89f,
            )
        }

        if (hasAny(normalized, "send a message", "text ", "sms ", "message ", "whatsapp")) {
            return LocalIntentDecision(
                intent = CommandIntent.MESSAGE,
                requiresLLM = false,
                toolRequest = ToolRequest("open_whatsapp"),
                confidence = 0.82f,
            )
        }

        if (hasAny(normalized, "set alarm", "wake me up", "alarm at")) {
            return LocalIntentDecision(
                intent = CommandIntent.ALARM,
                requiresLLM = false,
                toolRequest = ToolRequest("set_alarm", mapOf("hour" to "8", "minute" to "0")),
                confidence = 0.9f,
            )
        }

        if (hasAny(normalized, "remind me", "set a reminder", "reminder")) {
            return LocalIntentDecision(
                intent = CommandIntent.REMINDER,
                requiresLLM = false,
                toolRequest = ToolRequest(
                    "create_reminder",
                    mapOf(
                        "title" to "Reminder",
                        "date" to java.time.LocalDate.now().toString(),
                        "time" to "09:00",
                    )
                ),
                confidence = 0.88f,
            )
        }

        if (hasAny(normalized, "calendar", "my schedule", "open calendar", "upcoming events")) {
            return LocalIntentDecision(
                intent = CommandIntent.CALENDAR,
                requiresLLM = false,
                toolRequest = ToolRequest("open_calendar"),
                confidence = 0.9f,
            )
        }

        if (hasAny(normalized, "weather", "temperature", "forecast", "rain today", "sunny")) {
            return LocalIntentDecision(
                intent = CommandIntent.WEATHER,
                requiresLLM = false,
                toolRequest = ToolRequest("search_web", mapOf("query" to text)),
                confidence = 0.8f,
            )
        }

        if (hasAny(normalized, "remember", "what do you remember", "forget that")) {
            return LocalIntentDecision(
                intent = CommandIntent.MEMORY,
                requiresLLM = false,
                toolRequest = ToolRequest("memory_query"),
                confidence = 0.84f,
            )
        }

        if (hasAny(normalized, "explain", "what is", "how does", "why does", "teach me", "summarize")) {
            return LocalIntentDecision(
                intent = CommandIntent.CONVERSATION,
                requiresLLM = true,
                confidence = 0.76f,
            )
        }

        if (hasAny(normalized, "write code", "debug this", "fix this bug", "implement", "refactor", "program")) {
            return LocalIntentDecision(
                intent = CommandIntent.CODING,
                requiresLLM = true,
                confidence = 0.75f,
            )
        }

        if (hasAny(normalized, "study", "learn", "quiz", "practice", "revision")) {
            return LocalIntentDecision(
                intent = CommandIntent.STUDY,
                requiresLLM = true,
                confidence = 0.72f,
            )
        }

        if (hasAny(normalized, "play music", "song", "playlist", "audio")) {
            return LocalIntentDecision(
                intent = CommandIntent.MUSIC,
                requiresLLM = true,
                confidence = 0.7f,
            )
        }

        return LocalIntentDecision(
            intent = CommandIntent.CONVERSATION,
            requiresLLM = true,
            confidence = 0.5f,
        )
    }

    private fun hasAny(text: String, vararg phrases: String): Boolean = phrases.any { text.contains(it) }

    private fun extractAppName(input: String): String? {
        val opener = Regex("(?:open|launch|start)\\s+([A-Za-z0-9 ]{2,40})")
        val match = opener.find(input) ?: return null
        val candidate = match.groupValues[1].trim().lowercase()
        return candidate.ifBlank { null }
    }

    private fun inferPackageName(appName: String): String {
        return when {
            appName.contains("youtube") -> "com.google.android.youtube"
            appName.contains("whatsapp") -> "com.whatsapp"
            appName.contains("maps") -> "com.google.android.apps.maps"
            appName.contains("chrome") || appName.contains("browser") -> "com.android.chrome"
            appName.contains("calendar") -> "com.android.calendar"
            appName.contains("settings") -> "android.settings"
            else -> "com.google.android.youtube"
        }
    }

    private fun extractSearchQuery(text: String): String {
        val after = text.substringAfter("search for", missingDelimiterValue = text)
            .substringAfter("find", missingDelimiterValue = text)
            .substringAfter("latest", missingDelimiterValue = text)
            .substringAfter("look up", missingDelimiterValue = text)
            .trim()
        return after.removeSuffix(".").trim().ifBlank { text }
    }

    private fun extractCallTarget(text: String): String {
        val target = Regex("(?:call|phone|dial)\\s+(?:me\s+)?([A-Za-z0-9 .'-]+)", RegexOption.IGNORE_CASE)
            .find(text)?.groupValues?.getOrNull(1)
        return target?.trim().orEmpty()
    }
}

class CommandRouter(
    private val registry: ToolRegistry,
    private val aiToolGenerator: LLMToolGenerator,
    private val classifier: LocalIntentClassifier = LocalIntentClassifier(),
) {

    suspend fun route(input: String): RouteResult {
        val decision = classifier.classify(input)

        val request = when {
            decision.toolRequest != null -> decision.toolRequest
            decision.requiresLLM -> aiToolGenerator.generateToolRequest(input)
            else -> null
        }

        if (request == null) {
            return RouteResult(
                intent = decision.intent,
                requiresLLM = decision.requiresLLM,
                finalResponse = when {
                    decision.requiresLLM -> "I need to reason about that request before acting."
                    else -> "I can help with that."
                },
            )
        }

        val tool = registry.getTool(request.tool)
        if (tool == null) {
            return RouteResult(
                intent = decision.intent,
                requiresLLM = decision.requiresLLM,
                toolRequest = request,
                toolResult = ToolResult(false, null, "Tool '$${request.tool}' is not allowed or not available."),
                finalResponse = "I can’t use that tool right now because it isn’t on the approved allowlist.",
            )
        }

        val validatedArgs = validateArguments(tool, request.arguments)
        if (validatedArgs == null) {
            return RouteResult(
                intent = decision.intent,
                requiresLLM = decision.requiresLLM,
                toolRequest = request,
                toolResult = ToolResult(false, null, "The tool call is missing required arguments or has invalid values."),
                finalResponse = "The request is incomplete, so I can’t safely execute it yet.",
            )
        }

        val result = runCatching { tool.execute(validatedArgs) }
            .getOrElse { throwable ->
                ToolResult(false, null, throwable.message ?: "Tool execution failed.")
            }

        return RouteResult(
            intent = decision.intent,
            requiresLLM = decision.requiresLLM,
            toolRequest = request,
            toolResult = result,
            finalResponse = buildFinalResponse(result),
        )
    }

    private fun validateArguments(tool: AssistantTool, arguments: Map<String, String>): Map<String, String>? {
        val normalized = arguments.filterValues { !it.isNullOrBlank() }
        for (parameter in tool.parameters) {
            if (parameter.required && normalized[parameter.name].isNullOrBlank()) {
                return null
            }
            val rawValue = normalized[parameter.name] ?: continue
            if (parameter.type.equals("integer", ignoreCase = true)) {
                rawValue.toIntOrNull() ?: return null
            }
            if (parameter.type.equals("boolean", ignoreCase = true)) {
                if (rawValue !in setOf("true", "false")) return null
            }
        }

        if (tool.name == "open_app") {
            val packageName = normalized["package_name"] ?: return null
            if (!ToolArgumentValidator.validatePackageName(packageName)) return null
        }

        if (tool.name == "open_browser" || tool.name == "search_web") {
            val value = normalized["url"] ?: normalized["query"] ?: return null
            if (tool.name == "search_web") {
                if (value.length > 200) return null
            } else if (!ToolArgumentValidator.isSafeUrl(value)) {
                return null
            }
        }

        if (tool.name == "make_call") {
            val phone = normalized["phone_number"]
            val contact = normalized["contact_name"]
            if (!phone.isNullOrBlank() && !ToolArgumentValidator.validatePhoneNumber(phone)) return null
            if (!contact.isNullOrBlank() && contact.length > 80) return null
        }

        if (tool.name == "create_reminder") {
            val title = normalized["title"] ?: return null
            val description = normalized["description"] ?: ""
            if (!ToolArgumentValidator.validateReminderText(title)) return null
            if (description.length > 500) return null
        }

        if (tool.name == "set_alarm") {
            val hour = normalized["hour"] ?: return null
            val minute = normalized["minute"] ?: return null
            if (!ToolArgumentValidator.validateAlarmValue(hour) || !ToolArgumentValidator.validateAlarmValue(minute)) return null
        }

        return normalized
    }

    private fun buildFinalResponse(result: ToolResult): String {
        return when {
            result.success -> result.message ?: "The action completed successfully."
            else -> result.error ?: result.message ?: "The tool request could not be executed."
        }
    }
}
