package com.priya.app.presentation.screens.settings

import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiProviderSettingsScreen(onBack: () -> Unit) {
    AIProviderSetupScreen(onComplete = onBack, onBack = onBack)
}