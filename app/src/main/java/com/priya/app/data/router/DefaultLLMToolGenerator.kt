package com.priya.app.data.router

import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.repository.AIRepository
import com.priya.app.domain.router.LLMToolGenerator
import com.priya.app.domain.router.ToolRequest
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLLMToolGenerator @Inject constructor(
    private val aiRepository: AIRepository,
) : LLMToolGenerator {

    override suspend fun generateToolRequest(prompt: String): ToolRequest? {
        val response = aiRepository.generateResponse(
            AIRequest(
                conversationId = "router",
                prompt = buildString {
                    append("You are a tool-router planner. Return only a JSON object with fields: tool and arguments. ")
                    append("Use only these tools: open_app, open_settings, open_dialer, make_call, open_whatsapp, open_browser, search_web, get_time, get_date, get_battery_status, get_storage_status, get_network_status, get_device_info, set_alarm, cancel_alarm, create_timer, create_reminder, delete_reminder, get_location, open_maps, open_calendar. ")
                    append("If the request is not a tool use, return {\"tool\":\"conversation\", \"arguments\":{}}. ")
                    append("User request: $prompt")
                },
                systemPrompt = "You are Priya's routing planner. Return only a valid JSON tool call. Never return natural language. Use exact tool names from the allowlist."
            )
        )

        if (!response.success || response.text.isBlank()) {
            return null
        }

        val extracted = extractJsonObject(response.text) ?: return null
        val toolName = extracted.optString("tool", "").trim()
        if (toolName.isBlank() || toolName == "conversation") {
            return null
        }

        val args = mutableMapOf<String, String>()
        val arguments = extracted.optJSONObject("arguments")
        if (arguments != null) {
            val keys = arguments.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                args[key] = arguments.opt(key)?.toString() ?: ""
            }
        }

        return ToolRequest(toolName, args)
    }

    private fun extractJsonObject(raw: String): JSONObject? {
        val start = raw.indexOf('{')
        val end = raw.lastIndexOf('}')
        if (start == -1 || end <= start) return null
        val jsonText = raw.substring(start, end + 1)
        return runCatching { JSONObject(jsonText) }.getOrNull()
    }
}
