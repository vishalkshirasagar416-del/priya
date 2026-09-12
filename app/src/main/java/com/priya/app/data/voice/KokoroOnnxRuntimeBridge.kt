package com.priya.app.data.voice

import android.util.Log
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KokoroOnnxRuntimeBridge @Inject constructor(
    private val modelManager: KokoroModelManager,
) {
    private val env = OrtEnvironment.getEnvironment()
    private var session: OrtSession? = null

    @Synchronized
    fun initializeOnce(): Result<OrtSession> {
        val modelPath = modelManager.getModelPath()
        if (modelPath.isNullOrBlank()) {
            return Result.failure(IllegalStateException("Kokoro model missing. Download it first."))
        }

        val file = File(modelPath)
        if (!file.exists() || file.length() <= 0L) {
            return Result.failure(IllegalStateException("Kokoro model file is missing or corrupted."))
        }

        session?.let { return Result.success(it) }

        return runCatching {
            val options = OrtSession.SessionOptions().apply {
                setIntraOpNumThreads(1)
                setInterOpNumThreads(1)
            }
            val newSession = env.createSession(modelPath, options)
            session = newSession
            Log.i("PriyaTTS", "[TTS] Kokoro ONNX session initialized from $modelPath")
            newSession
        }.onFailure { throwable ->
            Log.e("PriyaTTS", "[TTS] Kokoro ONNX session failed to initialize", throwable)
        }
    }

    fun isReady(): Boolean = session != null && modelManager.hasModel()

    fun close() {
        session?.close()
        session = null
    }
}
