package com.priya.app.presentation.navigation

sealed class PriyaScreens(val route: String) {
    data object Splash : PriyaScreens("splash")
    data object AISetup : PriyaScreens("ai_setup")
    data object MainAssistant : PriyaScreens("main_assistant")
    data object Settings : PriyaScreens("settings")
    data object Permissions : PriyaScreens("permissions")
    data object Memory : PriyaScreens("memory")
    data object AiProviderSettings : PriyaScreens("ai_provider_settings")
    data object VoiceSettings : PriyaScreens("voice_settings")
}
