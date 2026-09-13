package com.priya.app.services

import android.Manifest
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.priya.app.MainActivity
import com.priya.app.R
import com.priya.app.data.scheduler.NotificationHelper
import com.priya.app.domain.ai.AIRequest
import com.priya.app.domain.repository.AIRepository
import com.priya.app.domain.router.CommandRouter
import com.priya.app.domain.voice.SpeechToText
import com.priya.app.domain.voice.WakeWordDetector
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BackgroundAssistantService : Service() {

    @Inject lateinit var speechToText: SpeechToText
    @Inject lateinit var audioService: AudioService
    @Inject lateinit var aiRepository: AIRepository
    @Inject lateinit var commandRouter: CommandRouter
    @Inject lateinit var wakeWordDetector: WakeWordDetector

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var lastProcessedText: String? = null
    private var handlingRequest = false
    private var requireWakeWord = false

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.ensureChannel(this)
        startForegroundCompat(createNotification())
        observeSpeech()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            stopSelf()
            return START_NOT_STICKY
        }

        if (intent?.hasExtra(EXTRA_REQUIRE_WAKE_WORD) == true) {
            requireWakeWord = intent.getBooleanExtra(EXTRA_REQUIRE_WAKE_WORD, false)
        }

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            stopSelf()
            return START_NOT_STICKY
        }

        serviceScope.launch {
            audioService.startListening()
        }
        return START_STICKY
    }

    private fun observeSpeech() {
        serviceScope.launch {
            speechToText.state.collectLatest { state ->
                val text = state.finalText.trim()
                if (state.status.name == "PROCESSING" && text.isNotBlank() && text != lastProcessedText && !handlingRequest) {
                    lastProcessedText = text
                    val command = if (requireWakeWord) extractWakeWordCommand(text) else text
                    if (command != null) handleRequest(command)
                }
            }
        }
    }

    private fun handleRequest(text: String) {
        serviceScope.launch {
            handlingRequest = true
            val reply = runCatching {
                val route = commandRouter.route(text)
                when {
                    route.toolResult != null -> route.finalResponse.orEmpty()
                    route.requiresLLM -> aiRepository.generateResponse(
                        AIRequest(conversationId = "background-session", prompt = text)
                    ).let { response -> response.text.ifBlank { response.errorMessage.orEmpty() } }
                    else -> route.finalResponse.orEmpty()
                }
            }.getOrElse { throwable -> throwable.message ?: "Priya could not process that command." }

            if (reply.isNotBlank()) {
                audioService.speakText(reply)
            }
            handlingRequest = false
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                audioService.startListening()
            }
        }
    }

    private fun extractWakeWordCommand(text: String): String? {
        if (!wakeWordDetector.detectInAudio(text)) return null
        val wakeWord = wakeWordDetector.defaultWakeWords
            .firstOrNull { text.contains(it, ignoreCase = true) }
            ?: return ""
        return text.replace(wakeWord, "", ignoreCase = true).trim().ifBlank { "Hello Priya" }
    }

    private fun startForegroundCompat(notification: Notification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun createNotification(): Notification {
        val launchIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        return NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentTitle("Priya is listening")
            .setContentText("Priya can hear commands in the background.")
            .setContentIntent(launchIntent)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    override fun onDestroy() {
        serviceScope.launch { audioService.stopListening() }
        serviceScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        private const val NOTIFICATION_ID = 7001
        const val ACTION_START = "com.priya.app.action.START_BACKGROUND_ASSISTANT"
        const val ACTION_STOP = "com.priya.app.action.STOP_BACKGROUND_ASSISTANT"
        const val EXTRA_REQUIRE_WAKE_WORD = "require_wake_word"

        fun start(context: Context, requireWakeWord: Boolean = false) {
            val intent = Intent(context, BackgroundAssistantService::class.java).apply {
                action = ACTION_START
                putExtra(EXTRA_REQUIRE_WAKE_WORD, requireWakeWord)
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, BackgroundAssistantService::class.java).setAction(ACTION_STOP))
        }
    }
}