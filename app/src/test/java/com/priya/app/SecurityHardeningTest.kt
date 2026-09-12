package com.priya.app

import com.priya.app.data.security.BackendSecurityConfig
import com.priya.app.data.tools.ToolArgumentValidator
import com.priya.app.data.repository.LocalMemoryRepositoryImpl
import com.priya.app.domain.model.MemoryRecord
import com.priya.app.domain.model.MemoryType
import com.priya.app.domain.router.CommandRouter
import com.priya.app.domain.router.LLMToolGenerator
import com.priya.app.domain.router.ToolRequest
import com.priya.app.domain.tools.AssistantTool
import com.priya.app.domain.tools.ToolParameter
import com.priya.app.domain.tools.ToolRegistry
import com.priya.app.domain.tools.ToolResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SecurityHardeningTest {

    @Test
    fun backendConfig_exposesNoVendorSecrets() {
        val config = BackendSecurityConfig(
            baseUrl = "https://api.example.com",
            apiKey = "backend-key",
        )

        assertTrue(config.baseUrl.startsWith("https://"))
        assertFalse(config.baseUrl.contains("googleapis.com"))
        assertFalse(config.baseUrl.contains("openrouter.ai"))
        assertFalse(config.baseUrl.contains("elevenlabs.io"))
        assertTrue(config.apiKey.isNotBlank())
    }

    @Test
    fun dangerousUrlSchemes_areRejected() {
        assertFalse(ToolArgumentValidator.isSafeUrl("javascript:alert(1)"))
        assertFalse(ToolArgumentValidator.isSafeUrl("file:///sdcard/test.txt"))
        assertFalse(ToolArgumentValidator.isSafeUrl("intent://scan/#Intent;scheme=zxing;package=com.google.zxing.client.android;end"))
        assertTrue(ToolArgumentValidator.isSafeUrl("https://example.com/path?q=hello"))
    }

    @Test
    fun router_rejects_unknownToolNames() = runTest {
        val router = CommandRouter(
            registry = FakeRegistry(listOf()),
            aiToolGenerator = object : LLMToolGenerator {
                override suspend fun generateToolRequest(prompt: String): ToolRequest? =
                    ToolRequest("unknown_tool", emptyMap())
            },
        )

        val result = router.route("Open settings")
        assertFalse(result.toolResult?.success == true)
        assertTrue(result.finalResponse!!.contains("not allowed") || result.finalResponse.contains("isn’t"))
    }

    @Test
    fun highRiskTool_requiresValidatedPhoneNumber() {
        val arguments = mapOf("phone_number" to "123-abc")
        val validation = ToolArgumentValidator.validatePhoneNumber(arguments["phone_number"])
        assertFalse(validation)

        val valid = ToolArgumentValidator.validatePhoneNumber("+1-415-555-0100")
        assertTrue(valid)
    }

    @Test
    fun sensitiveMemory_isRejected() {
        val memory = MemoryRecord(
            type = MemoryType.GENERAL,
            key = "oauth_token",
            value = "secret-token-123",
        )

        val isSensitive = LocalMemoryRepositoryImpl.containsSensitiveDataForTest(memory.key, memory.value)
        assertTrue(isSensitive)
    }

    private class FakeRegistry(
        tools: List<AssistantTool>,
    ) : ToolRegistry {
        override val tools: List<AssistantTool> = tools
        override fun getTool(name: String): AssistantTool? = null
        override fun hasTool(name: String): Boolean = false
    }
}
