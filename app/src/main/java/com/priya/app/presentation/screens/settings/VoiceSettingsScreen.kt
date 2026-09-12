package com.priya.app.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priya.app.domain.voice.VoiceEnginePreference
import com.priya.app.presentation.viewmodel.VoiceSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceSettingsScreen(
    onBack: () -> Unit,
    viewModel: VoiceSettingsViewModel = hiltViewModel(),
) {
    val settings by viewModel.settings.collectAsState()
    val modelState by viewModel.modelState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Voice settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Voice Engine", style = MaterialTheme.typography.titleMedium)
            VoiceEngineChoice(
                selected = settings.selectedVoiceEngine,
                onSelect = viewModel::setVoiceEngine,
            )

            Divider()

            Text("Kokoro Voice", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(settings.kokoroVoice.ifBlank { "af_heart" })
                OutlinedButton(onClick = { viewModel.setKokoroVoice("af_heart") }) {
                    Text("Use af_heart")
                }
            }

            Divider()

            Text("Voice Speed", style = MaterialTheme.typography.titleMedium)
            Slider(
                value = settings.voiceSpeed,
                onValueChange = viewModel::setVoiceSpeed,
                valueRange = 0.5f..1.5f,
            )

            Text("Voice Pitch", style = MaterialTheme.typography.titleMedium)
            Slider(
                value = settings.voicePitch,
                onValueChange = viewModel::setVoicePitch,
                valueRange = 0.5f..1.5f,
            )

            Divider()

            Text("Kokoro model", style = MaterialTheme.typography.titleMedium)
            Text(modelState.message)
            val modelError = modelState.error
            if (modelError != null) {
                Text(modelError, color = MaterialTheme.colorScheme.error)
            }
            Button(onClick = { viewModel.ensureKokoroModel() }) {
                Text("Initialize Kokoro")
            }
        }
    }
}

@Composable
private fun VoiceEngineChoice(
    selected: VoiceEnginePreference,
    onSelect: (VoiceEnginePreference) -> Unit,
) {
    val options = listOf(
        VoiceEnginePreference.AUTO to "Auto / Smart Fallback",
        VoiceEnginePreference.ELEVENLABS to "ElevenLabs",
        VoiceEnginePreference.KOKORO to "Kokoro",
        VoiceEnginePreference.ANDROID_LOCAL to "Android Local",
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.forEach { (value, label) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(label)
                androidx.compose.material3.RadioButton(
                    selected = selected == value,
                    onClick = { onSelect(value) },
                )
            }
        }
    }
}
