package com.priya.app

import com.priya.app.domain.router.CommandIntent
import com.priya.app.domain.router.CommandRouter
import com.priya.app.domain.router.LocalIntentClassifier
import com.priya.app.domain.tools.AssistantTool
import com.priya.app.domain.tools.ToolParameter
import com.priya.app.domain.tools.ToolRegistry
import com.priya.app.domain.tools.ToolResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CommandRouterTest {

    @Test
    fun localIntentClassifier_routes_time_and_battery_queries_without_llm() {
        val classifier = LocalIntentClassifier()

        val time = classifier.classify("What time is it?")
        assertEquals(CommandIntent.SYSTEM_STATUS, time.intent)
        assertFalse(time.requiresLLM)
        assertEquals("get_time", time.toolRequest?.tool)

        val battery = classifier.classify("What's my battery?")
        assertEquals(CommandIntent.SYSTEM_STATUS, battery.intent)
        assertFalse(battery.requiresLLM)
        assertEquals("get_battery_status", battery.toolRequest?.tool)
    }

    @Test
    fun localIntentClassifier_routes_open_app_and_search_queries() {
        val classifier = LocalIntentClassifier()

        val youtube = classifier.classify("Open YouTube.")
        assertEquals(CommandIntent.ANDROID_ACTION, youtube.intent)
        assertFalse(youtube.requiresLLM)
        assertEquals("open_app", youtube.toolRequest?.tool)
        assertTrue(youtube.toolRequest?.arguments?.containsKey("package_name") == true)

        val news = classifier.classify("Find the latest AI news.")
        assertEquals(CommandIntent.WEB_SEARCH, news.intent)
        assertFalse(news.requiresLLM)
        assertEquals("search_web", news.toolRequest?.tool)
    }

    @Test
    fun router_requires_llm_for_general_explanations() {
        val classifier = LocalIntentClassifier()
        val result = classifier.classify("Explain how JWT refresh tokens work.")

        assertEquals(CommandIntent.CONVERSATION, result.intent)
        assertTrue(result.requiresLLM)
    }

    @Test
    fun router_rejects_invalid_llm_tool_requests() = runTest {
        val router = CommandRouter(
            registry = FakeToolRegistry(listOf(FakeOpenAppTool(), FakeGetTimeTool())),
            aiToolGenerator = object : LLMToolGenerator {
                override suspend fun generateToolRequest(prompt: String): ToolRequest? =
                    ToolRequest("unknown_tool", mapOf("package_name" to "com.google.android.youtube"))
            },
        )

        val result = router.route("Open YouTube.")

        assertNotNull(result)
        assertEquals(CommandIntent.ANDROID_ACTION, result.intent)
        assertTrue(result.toolResult == null || !result.toolResult.success)
    }

    @Test
    fun router_executes_valid_llm_structured_tool_requests() = runTest {
        val router = CommandRouter(
            registry = FakeToolRegistry(listOf(FakeOpenAppTool(), FakeGetTimeTool())),
            aiToolGenerator = object : LLMToolGenerator {
                override suspend fun generateToolRequest(prompt: String): ToolRequest? =
                    ToolRequest("open_app", mapOf("package_name" to "com.google.android.youtube"))
            },
        )

        val result = router.route("Open YouTube.")

        assertTrue(result.toolResult != null)
        assertTrue(result.toolResult.success)
        assertEquals("open_app", result.toolRequest?.tool)
    }

    private class FakeToolRegistry(
        tools: List<AssistantTool>,
    ) : ToolRegistry {
        override val tools: List<AssistantTool> = tools
        override fun getTool(name: String): AssistantTool? = tools.firstOrNull { it.name == name }
        override fun hasTool(name: String): Boolean = tools.any { it.name == name }
    }

    private class FakeOpenAppTool : AssistantTool {
        override val name: String = "open_app"
        override val description: String = "Open an app"
        override val parameters: List<ToolParameter> = listOf(
            ToolParameter("package_name", "string", true, "Package name")
        )

        override suspend fun execute(arguments: Map<String, String>): ToolResult {
            return ToolResult(true, "Opened")
        }
    }

    private class FakeGetTimeTool : AssistantTool {
        override val name: String = "get_time"
        override val description: String = "Get the time"
        override val parameters: List<ToolParameter> = emptyList()

        override suspend fun execute(arguments: Map<String, String>): ToolResult {
            return ToolResult(true, "Current time: 10:00")
        }
    }
}
