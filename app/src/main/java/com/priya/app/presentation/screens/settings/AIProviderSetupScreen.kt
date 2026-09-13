package com.priya.app.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priya.app.domain.ai.AIProviderType
import com.priya.app.presentation.viewmodel.AIConfigViewModel

@Composable
fun AIProviderSetupScreen(
    onComplete: () -> Unit,
    onBack: (() -> Unit)? = null,
    allowSkip: Boolean = false,
    viewModel: AIConfigViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val existing = state.config
    var gemini by remember { mutableStateOf("") }
    var openRouter by remember { mutableStateOf("") }
    var elevenLabs by remember { mutableStateOf("") }
    var primary by remember { mutableStateOf(existing.primaryProvider) }
    var showGemini by remember { mutableStateOf(false) }
    var showOpenRouter by remember { mutableStateOf(false) }
    var showElevenLabs by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (allowSkip) "Welcome to Priya" else "AI providers") },
                navigationIcon = {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            if (allowSkip) {
                Text("Let's connect your AI providers", style = MaterialTheme.typography.headlineSmall)
                Text(
                    "Priya needs your own AI API keys to use AI features. Your keys are stored locally on this device.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                Text("Update provider keys", style = MaterialTheme.typography.headlineSmall)
                Text(
                    "Existing keys are never displayed. Enter a new value only when replacing one.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            SecretField("Gemini API key", "Enter Gemini API key", gemini, showGemini, { gemini = it }, { showGemini = !showGemini }, "Gemini")
            ProviderStatus("Gemini", existing.geminiApiKey.isNotBlank()) {
                viewModel.remove(AIProviderType.GEMINI)
            }

            SecretField("OpenRouter API key", "Enter OpenRouter API key", openRouter, showOpenRouter, { openRouter = it }, { showOpenRouter = !showOpenRouter }, "OpenRouter")
            ProviderStatus("OpenRouter", existing.openRouterApiKey.isNotBlank()) {
                viewModel.remove(AIProviderType.OPENROUTER)
            }

            SecretField("ElevenLabs API key (optional)", "Enter ElevenLabs API key", elevenLabs, showElevenLabs, { elevenLabs = it }, { showElevenLabs = !showElevenLabs }, "ElevenLabs")
            ProviderStatus("ElevenLabs", existing.elevenLabsApiKey.isNotBlank()) {
                viewModel.remove(AIProviderType.NONE)
            }

            Text("Primary AI provider", style = MaterialTheme.typography.titleMedium)
            ProviderChoice("Gemini", AIProviderType.GEMINI, primary) { primary = it }
            ProviderChoice("OpenRouter", AIProviderType.OPENROUTER, primary) { primary = it }
            Text(
                "The selected provider is tried first. The other configured provider remains available as fallback.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            Button(
                onClick = {
                    if (viewModel.save(gemini, openRouter, elevenLabs, primary, allowSkip)) onComplete()
                },
                modifier = Modifier.fillMaxWidth(),
            ) { Text(if (allowSkip) "Save & Continue" else "Save Changes") }

            if (allowSkip) {
                OutlinedButton(
                    onClick = { viewModel.skipSetup(); onComplete() },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Skip for now") }
            }
        }
    }
}

@Composable
private fun SecretField(
    label: String,
    placeholder: String,
    value: String,
    visible: Boolean,
    onValueChange: (String) -> Unit,
    onToggleVisibility: () -> Unit,
    providerName: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onToggleVisibility) {
                Icon(
                    imageVector = if (visible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Toggle $providerName key visibility",
                )
            }
        },
    )
}

@Composable
private fun ProviderStatus(name: String, configured: Boolean, onRemove: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("$name: ${if (configured) "Configured" else "Not configured"}", style = MaterialTheme.typography.bodySmall)
        if (configured) OutlinedButton(onClick = onRemove) { Text("Remove") }
    }
}

@Composable
private fun ProviderChoice(name: String, value: AIProviderType, selected: AIProviderType, onSelect: (AIProviderType) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected == value, onClick = { onSelect(value) })
        Text(name)
    }
}