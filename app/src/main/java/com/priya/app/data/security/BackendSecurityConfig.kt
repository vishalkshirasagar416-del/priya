package com.priya.app.data.security

import javax.inject.Inject

data class BackendSecurityConfig(
    val baseUrl: String = "https://api.priya.app",
    val apiKey: String = "",
)

class BackendSecurityConfigProvider @Inject constructor() {
    fun provide(): BackendSecurityConfig = BackendSecurityConfig(
        baseUrl = "https://api.priya.app",
        apiKey = "",
    )
}
