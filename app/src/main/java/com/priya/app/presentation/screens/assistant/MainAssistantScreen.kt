package com.priya.app.presentation.screens.assistant

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Study
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.SignalWifiOff
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priya.app.core.AssistantStatus
import com.priya.app.domain.model.AssistantMessage
import com.priya.app.services.BackgroundAssistantService
import com.priya.app.avatar.PriyaAvatarView
import com.priya.app.presentation.viewmodel.PriyaViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun MainAssistantScreen(
    onOpenSettings: () -> Unit,
    onNavigateToPermissions: () -> Unit,
    viewModel: PriyaViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    var messageText by remember { mutableStateOf("") }
    var backgroundEnabled by remember { mutableStateOf(false) }
    var requireWakeWord by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val microphoneLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                viewModel.toggleListening()
            } else {
                viewModel.updateStatus(AssistantStatus.ERROR)
            }
        },
    )

    val quickActions = listOf(
        "Weather" to Icons.Default.SmartToy,
        "Reminders" to Icons.Default.NotificationsActive,
        "Battery" to Icons.Default.BatteryChargingFull,
        "Study" to Icons.Default.Study,
        "Music" to Icons.Default.MusicNote,
    )

    val statusLabel = when {
        uiState.assistantStatus == AssistantStatus.ERROR -> "Error"
        uiState.assistantStatus == AssistantStatus.OFFLINE -> "Offline"
        uiState.isSpeaking -> "Speaking"
        uiState.isListening -> "Listening"
        uiState.isProcessing -> "Thinking"
        else -> "Idle"
    }

    val statusMessage = when (uiState.assistantStatus) {
        AssistantStatus.ERROR -> "Something interrupted the moment. Let’s try that again."
        AssistantStatus.OFFLINE -> "You’re offline. Priya will reconnect automatically when the connection is back."
        AssistantStatus.LISTENING -> "Listening for your next request..."
        AssistantStatus.PROCESSING, AssistantStatus.THINKING -> "Thinking through the best response..."
        AssistantStatus.SPEAKING -> "Priya is responding with a calm, natural voice."
        else -> "Ready when you are."
    }

    val microphoneColor = when {
        uiState.assistantStatus == AssistantStatus.ERROR -> Color(0xFFF87171)
        uiState.assistantStatus == AssistantStatus.OFFLINE -> Color(0xFF94A3B8)
        uiState.isListening -> Color(0xFF34D399)
        uiState.isSpeaking -> Color(0xFFFBBF24)
        uiState.isProcessing -> Color(0xFF8B5CF6)
        else -> MaterialTheme.colorScheme.primary
    }

    LaunchedEffect(uiState.assistantStatus) {
        if (uiState.assistantStatus == AssistantStatus.SPEAKING) {
            delay(2200)
            viewModel.updateStatus(AssistantStatus.IDLE)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface,
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Priya",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                color = when (uiState.assistantStatus) {
                                    AssistantStatus.ERROR -> Color(0xFFF87171)
                                    AssistantStatus.OFFLINE -> Color(0xFF94A3B8)
                                    AssistantStatus.LISTENING -> Color(0xFF34D399)
                                    AssistantStatus.SPEAKING -> Color(0xFFFBBF24)
                                    AssistantStatus.PROCESSING, AssistantStatus.THINKING -> Color(0xFFA78BFA)
                                    else -> Color(0xFF22C55E)
                                }
                            )
                    )
                    Text(
                        text = statusLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            IconButton(
                onClick = onOpenSettings,
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
                        shape = CircleShape,
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Open settings",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                            )
                        )
                    )
                    .padding(18.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    AndroidView(
                        factory = { PriyaAvatarView(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        update = { avatarView ->
                            avatarView.setSpeaking(uiState.isSpeaking)
                            if (uiState.isSpeaking) avatarView.setEmotion("happy")
                        },
                        onRelease = { avatarView -> avatarView.release() },
                    )

                    Text(
                        text = statusMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Text(
                    text = "Quick actions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    quickActions.forEach { (label, icon) ->
                        AssistChip(
                            onClick = {
                                viewModel.addUserMessage("$label")
                                viewModel.updateStatus(AssistantStatus.THINKING)
                                scope.launch {
                                    delay(900)
                                    viewModel.addAssistantMessage(
                                        when (label) {
                                            "Weather" -> "The forecast is clear with a mild breeze and 24°C today."
                                            "Reminders" -> "Your 7:30 PM reminder is scheduled and ready."
                                            "Battery" -> "Battery is at 84% and charging normally."
                                            "Study" -> "You have three study tasks left. Want a quick revision plan?"
                                            "Music" -> "I found a calm focus playlist and queued it for you."
                                            else -> "I can help with that."
                                        }
                                    )
                                    viewModel.updateStatus(AssistantStatus.SPEAKING)
                                    delay(2200)
                                    viewModel.updateStatus(AssistantStatus.IDLE)
                                }
                            },
                            label = { Text(label) },
                            leadingIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    modifier = Modifier.size(18.dp),
                                )
                            },
                            modifier = Modifier.height(38.dp),
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    if (uiState.assistantStatus == AssistantStatus.OFFLINE) {
                        onNavigateToPermissions()
                        return@Button
                    }

                    if (uiState.assistantStatus == AssistantStatus.ERROR) {
                        viewModel.updateStatus(AssistantStatus.IDLE)
                        return@Button
                    }

                    if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        viewModel.toggleListening()
                    } else {
                        microphoneLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = microphoneColor,
                    contentColor = Color.White,
                ),
                modifier = Modifier.size(92.dp),
            ) {
                Icon(
                    imageVector = if (uiState.isListening) Icons.Outlined.Mic else Icons.Default.Mic,
                    contentDescription = "Microphone button",
                    modifier = Modifier.size(34.dp),
                )
            }
        }

        OutlinedButton(
            onClick = {
                if (backgroundEnabled) {
                    BackgroundAssistantService.stop(context)
                    backgroundEnabled = false
                } else if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    BackgroundAssistantService.start(context, requireWakeWord)
                    backgroundEnabled = true
                } else {
                    onNavigateToPermissions()
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (backgroundEnabled) "Stop background assistant" else "Start background assistant")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Require wake word", style = MaterialTheme.typography.titleSmall)
                Text(
                    "Only act after hearing Priya, Hey Priya, or Hi Priya.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Switch(
                checked = requireWakeWord,
                onCheckedChange = { enabled ->
                    requireWakeWord = enabled
                    if (backgroundEnabled) {
                        BackgroundAssistantService.start(context, enabled)
                    }
                },
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Conversation",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )

                    if (uiState.assistantStatus == AssistantStatus.OFFLINE) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SignalWifiOff,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text(
                                text = "Offline",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                if (uiState.messages.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 96.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                                shape = RoundedCornerShape(18.dp),
                            )
                            .padding(horizontal = 16.dp, vertical = 18.dp),
                    ) {
                        Text(
                            text = "No messages yet. Ask Priya something to get started.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(uiState.messages) { message ->
                            AssistantMessageBubble(message = message)
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Message Priya") },
                        singleLine = true,
                    )
                    Button(
                        onClick = {
                            val text = messageText.trim()
                            if (text.isNotBlank()) {
                                viewModel.sendMessage(text)
                                messageText = ""
                            }
                        },
                        enabled = messageText.isNotBlank() && !uiState.isProcessing,
                    ) {
                        Text("Send")
                    }
                }
            }
        }
    }
}

@Composable
private fun AssistantAvatar(
    assistantStatus: AssistantStatus,
    isListening: Boolean,
    isSpeaking: Boolean,
    isProcessing: Boolean,
) {
    val accent = when {
        assistantStatus == AssistantStatus.ERROR -> Color(0xFFF87171)
        assistantStatus == AssistantStatus.OFFLINE -> Color(0xFF94A3B8)
        isSpeaking -> Color(0xFFFBBF24)
        isListening -> Color(0xFF34D399)
        isProcessing -> Color(0xFF8B5CF6)
        else -> MaterialTheme.colorScheme.primary
    }

    Box(
        modifier = Modifier
            .size(156.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        accent.copy(alpha = 0.8f),
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.95f),
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
                    )
                )
            )
            .border(
                width = 1.5.dp,
                color = Color.White.copy(alpha = 0.4f),
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (assistantStatus == AssistantStatus.LISTENING || isListening) {
            AnimatedAudioWave(color = Color(0xFFDCFCE7))
        } else if (assistantStatus == AssistantStatus.PROCESSING || assistantStatus == AssistantStatus.THINKING || isProcessing) {
            AnimatedProcessingRing(color = Color(0xFFE9D5FF))
        } else if (assistantStatus == AssistantStatus.SPEAKING || isSpeaking) {
            AnimatedVoicePulse(color = Color(0xFFFEF3C7))
        } else if (assistantStatus == AssistantStatus.ERROR) {
            Text(
                text = "!",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        } else if (assistantStatus == AssistantStatus.OFFLINE) {
            Icon(
                imageVector = Icons.Outlined.SignalWifiOff,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.95f),
                modifier = Modifier.size(52.dp),
            )
        } else {
            Text(
                text = "P",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun AnimatedAudioWave(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "audio-wave")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "audio-wave-phase",
    )

    Row(
        modifier = Modifier
            .width(120.dp)
            .height(72.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        repeat(6) { index ->
            val wave = ((sin((phase * 2f * PI.toFloat()) + index * 0.7f) + 1f) / 2f)
            val height = 18.dp + (wave * 46.dp.value).dp
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(height)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
private fun AnimatedProcessingRing(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "processing-ring")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "ring-rotation",
    )

    Box(
        modifier = Modifier
            .size(110.dp)
            .clip(CircleShape)
            .background(color.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(100.dp)) {
            drawCircle(
                color = color.copy(alpha = 0.9f),
                radius = size.minDimension / 2f - 10f,
                style = Stroke(width = 5f),
                center = center,
            )
            drawArc(
                color = color,
                startAngle = -90f + rotation,
                sweepAngle = 220f,
                useCenter = false,
                style = Stroke(width = 5f, cap = StrokeCap.Round),
            )
        }
    }
}

@Composable
private fun AnimatedVoicePulse(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "voice-pulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "voice-pulse-scale",
    )

    Box(
        modifier = Modifier
            .size((92 * pulse).dp)
            .clip(CircleShape)
            .background(color.copy(alpha = 0.18f)),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.28f)),
        )
        Text(
            text = "♪",
            style = MaterialTheme.typography.headlineMedium,
            color = color,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun AssistantMessageBubble(message: AssistantMessage) {
    val alignment = if (message.isUser) Alignment.End else Alignment.Start
    val containerColor = if (message.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = containerColor,
                    shape = RoundedCornerShape(18.dp),
                )
                .padding(horizontal = 14.dp, vertical = 10.dp),
        ) {
            Text(
                text = message.text,
                color = if (message.isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
