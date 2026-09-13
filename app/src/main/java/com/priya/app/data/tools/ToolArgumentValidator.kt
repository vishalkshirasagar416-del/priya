package com.priya.app.data.tools

import android.net.Uri
import java.util.Locale

object ToolArgumentValidator {
    private val safeHttpHosts = setOf("example.com", "api.priya.app")

    fun validatePackageName(value: String?): Boolean {
        if (value.isNullOrBlank()) return false
        return value.matches(Regex("^[+()\\d\\s-]{7,20}$")) &&
            !value.startsWith(".") &&
            !value.contains("..")
    }

    fun validatePhoneNumber(value: String?): Boolean {
        if (value.isNullOrBlank()) return false
        return value.matches(Regex("^[+()\\d\s-]{7,20}$"))
    }

    fun validateReminderText(value: String?): Boolean {
        if (value.isNullOrBlank()) return false
        return value.length in 1..200
    }

    fun validateAlarmValue(value: String?): Boolean {
        if (value.isNullOrBlank()) return false
        return value.toIntOrNull() in 0..23 || value.toIntOrNull() in 0..59
    }

    fun validateUrl(value: String?): Boolean {
        val sanitized = value?.trim() ?: return false
        return try {
            val uri = Uri.parse(sanitized)
            val scheme = uri.scheme?.lowercase(Locale.ROOT) ?: return false
            val host = uri.host?.lowercase(Locale.ROOT) ?: return false
            val isSafeScheme = scheme == "https" || scheme == "http"
            val isSafeHost = host in safeHttpHosts || host.endsWith(".priya.app") || host.endsWith(".example.com")
            isSafeScheme && isSafeHost
        } catch (_: Throwable) {
            false
        }
    }

    fun isSafeUrl(value: String?): Boolean = validateUrl(value)
}
