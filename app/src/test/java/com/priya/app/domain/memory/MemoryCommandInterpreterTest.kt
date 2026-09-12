package com.priya.app.domain.memory

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MemoryCommandInterpreterTest {

    @Test
    fun `remember command stores language preference`() {
        val result = MemoryCommandInterpreter().interpret("Remember that I prefer Kannada.")

        assertEquals(MemoryCommandType.STORE, result.commandType)
        assertEquals("preferred_language", result.key)
        assertEquals("Kannada", result.value)
    }

    @Test
    fun `question command asks for known memories`() {
        val result = MemoryCommandInterpreter().interpret("What do you remember about me?")

        assertEquals(MemoryCommandType.QUERY, result.commandType)
        assertEquals("profile", result.key)
    }

    @Test
    fun `forget command clears a memory entry`() {
        val result = MemoryCommandInterpreter().interpret("Forget that.")

        assertEquals(MemoryCommandType.DELETE, result.commandType)
        assertTrue(result.key.isNotBlank())
    }
}
