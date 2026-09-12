package com.priya.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.priya.app.presentation.screens.assistant.MainAssistantScreen
import com.priya.app.presentation.screens.memory.MemoryScreen
import com.priya.app.presentation.screens.permissions.PermissionsScreen
import com.priya.app.presentation.screens.settings.SettingsScreen
import com.priya.app.presentation.screens.settings.VoiceSettingsScreen
import com.priya.app.presentation.screens.splash.SplashScreen

@Composable
fun PriyaNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PriyaScreens.Splash.route
    ) {
        composable(PriyaScreens.Splash.route) {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(PriyaScreens.MainAssistant.route) {
                        popUpTo(PriyaScreens.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(PriyaScreens.MainAssistant.route) {
            MainAssistantScreen(
                onOpenSettings = { navController.navigate(PriyaScreens.Settings.route) },
                onNavigateToPermissions = { navController.navigate(PriyaScreens.Permissions.route) }
            )
        }
        composable(PriyaScreens.Settings.route) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onOpenPermissions = { navController.navigate(PriyaScreens.Permissions.route) },
                onOpenMemory = { navController.navigate(PriyaScreens.Memory.route) },
                onOpenAiProvider = { navController.navigate(PriyaScreens.AiProviderSettings.route) },
                onOpenVoice = { navController.navigate(PriyaScreens.VoiceSettings.route) }
            )
        }
        composable(PriyaScreens.Permissions.route) {
            PermissionsScreen(onBack = { navController.popBackStack() })
        }
        composable(PriyaScreens.Memory.route) {
            MemoryScreen(onBack = { navController.popBackStack() })
        }
        composable(PriyaScreens.AiProviderSettings.route) {
            PlaceholderScreen(
                title = "AI Provider Settings",
                onBack = { navController.popBackStack() }
            )
        }
        composable(PriyaScreens.VoiceSettings.route) {
            VoiceSettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
