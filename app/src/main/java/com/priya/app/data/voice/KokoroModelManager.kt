package com.priya.app.data.voice

import android.content.Context
import android.util.Log
import com.priya.app.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

enum class KokoroDownloadStatus {
    IDLE,
    DOWNLOADING,
    READY,
    ERROR,
}

data class KokoroModelState(
    val status: KokoroDownloadStatus = KokoroDownloadStatus.IDLE,
    val progress: Float = 0f,
    val message: String = "Waiting for model setup",
    val modelPath: String? = null,
    val error: String? = null,
)

@Singleton
class KokoroModelManager @Inject constructor(
    private val context: Context,
) {
    private val modelDir = File(context.filesDir, "kokoro")
    private val modelFile = File(modelDir, "model.onnx")
    private val configFile = File(modelDir, "model_config.json")
    private val _state = MutableStateFlow(KokoroModelState())
    val state: StateFlow<KokoroModelState> = _state.asStateFlow()

    @Volatile
    private var initialized: Boolean = false

    fun hasModel(): Boolean = modelFile.exists() && modelFile.length() > 0L && configFile.exists()

    fun getModelPath(): String? = if (hasModel()) modelFile.absolutePath else null

    suspend fun ensureModel(): Result<File> = withContext(Dispatchers.IO) {
        if (hasModel()) {
            initialized = true
            _state.value = KokoroModelState(
                status = KokoroDownloadStatus.READY,
                progress = 1f,
                message = "Kokoro model ready",
                modelPath = modelFile.absolutePath,
            )
            return@withContext Result.success(modelFile)
        }

        val url = BuildConfig.KOKORO_MODEL_URL.takeIf { it.isNotBlank() }
            ?: return@withContext Result.failure(IllegalStateException("Kokoro model URL is not configured. Add KOKORO_MODEL_URL in local.properties."))

        _state.value = KokoroModelState(
            status = KokoroDownloadStatus.DOWNLOADING,
            progress = 0f,
            message = "Downloading Kokoro model",
        )

        runCatching {
            val remote = URL(url)
            val connection = remote.openConnection() as HttpURLConnection
            connection.connectTimeout = 30000
            connection.readTimeout = 30000
            connection.requestMethod = "GET"
            val length = connection.contentLengthLong.takeIf { it > 0 } ?: 0L
            if (connection.responseCode !in 200..299) {
                throw IllegalStateException("Model download failed: HTTP ${connection.responseCode}")
            }

            modelDir.mkdirs()
            val tempFile = File(modelDir, "model.onnx.part")
            tempFile.parentFile?.mkdirs()

            connection.inputStream.use { input ->
                FileOutputStream(tempFile).use { output ->
                    val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                    var total = 0L
                    var read: Int
                    while (input.read(buffer).also { read = it } != -1) {
                        output.write(buffer, 0, read)
                        total += read.toLong()
                        if (length > 0L) {
                            val progress = (total.toFloat() / length.toFloat()).coerceIn(0f, 1f)
                            _state.value = _state.value.copy(
                                status = KokoroDownloadStatus.DOWNLOADING,
                                progress = progress,
                                message = "Downloading Kokoro model (${(progress * 100).toInt()}%)",
                            )
                        }
                    }
                }
            }

            if (!tempFile.exists() || tempFile.length() <= 0L) {
                throw IllegalStateException("Downloaded Kokoro model is empty or invalid.")
            }

            tempFile.renameTo(modelFile)
            val config = File(modelDir, "model_config.json")
            config.writeText("{\"voice\":\"af_heart\",\"format\":\"onnx\"}")
            initialized = true
            _state.value = KokoroModelState(
                status = KokoroDownloadStatus.READY,
                progress = 1f,
                message = "Kokoro model ready",
                modelPath = modelFile.absolutePath,
            )
            modelFile
        }.fold(
            onSuccess = { file ->
                Result.success(file)
            },
            onFailure = { throwable ->
                initialized = false
                _state.value = KokoroModelState(
                    status = KokoroDownloadStatus.ERROR,
                    progress = 0f,
                    message = "Failed to prepare Kokoro model",
                    error = throwable.message ?: "Unknown Kokoro model error",
                )
                Log.e("PriyaTTS", "Kokoro model setup failed: ${throwable.message}", throwable)
                Result.failure(throwable)
            },
        )
    }

    fun resetModel() {
        if (modelFile.exists()) modelFile.delete()
        if (configFile.exists()) configFile.delete()
        initialized = false
        _state.value = KokoroModelState(
            status = KokoroDownloadStatus.IDLE,
            progress = 0f,
            message = "Kokoro model reset",
        )
    }

    fun isInitialized(): Boolean = initialized
}
