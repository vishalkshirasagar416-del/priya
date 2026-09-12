package com.priya.app.data.voice;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ2\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0014H\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u001cH\u0002J\u0010\u0010(\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0014H\u0016J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020&0*H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b+\u0010,J,\u0010-\u001a\b\u0012\u0004\u0012\u00020&0*2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020.H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b/\u00100J\u001c\u00101\u001a\b\u0012\u0004\u0012\u00020&0*H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b2\u0010,J\u0010\u00103\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\u0014H\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0018X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00065"}, d2 = {"Lcom/priya/app/data/voice/KokoroTTSProvider;", "Lcom/priya/app/domain/voice/TextToSpeechEngine;", "context", "Landroid/content/Context;", "modelManager", "Lcom/priya/app/data/voice/KokoroModelManager;", "voicePreferences", "Lcom/priya/app/data/voice/VoicePreferences;", "onnxBridge", "Lcom/priya/app/data/voice/KokoroOnnxRuntimeBridge;", "(Landroid/content/Context;Lcom/priya/app/data/voice/KokoroModelManager;Lcom/priya/app/data/voice/VoicePreferences;Lcom/priya/app/data/voice/KokoroOnnxRuntimeBridge;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/priya/app/core/AudioState;", "isConfigured", "", "()Z", "mediaPlayer", "Landroid/media/MediaPlayer;", "name", "", "getName", "()Ljava/lang/String;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "generateAudioFile", "Ljava/io/File;", "modelFile", "text", "voiceName", "speed", "", "pitch", "getSupportedVoice", "requestedVoice", "playAudioFile", "", "file", "shouldInterruptOn", "shutdown", "Lkotlin/Result;", "shutdown-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "speak", "Lcom/priya/app/domain/voice/VoiceContext;", "speak-0E7RQCE", "(Ljava/lang/String;Lcom/priya/app/domain/voice/VoiceContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "stop-IoAF18A", "supportsLanguage", "languageCode", "app_debug"})
public final class KokoroTTSProvider implements com.priya.app.domain.voice.TextToSpeechEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.KokoroModelManager modelManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.VoicePreferences voicePreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.KokoroOnnxRuntimeBridge onnxBridge = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.priya.app.core.AudioState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AudioState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = "Kokoro";
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer mediaPlayer;
    
    @javax.inject.Inject()
    public KokoroTTSProvider(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.KokoroModelManager modelManager, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.VoicePreferences voicePreferences, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.KokoroOnnxRuntimeBridge onnxBridge) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AudioState> getState() {
        return null;
    }
    
    @java.lang.Override()
    public boolean isConfigured() {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @java.lang.Override()
    public boolean supportsLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
        return false;
    }
    
    @java.lang.Override()
    public boolean shouldInterruptOn(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return false;
    }
    
    private final java.lang.String getSupportedVoice(java.lang.String requestedVoice) {
        return null;
    }
    
    private final java.io.File generateAudioFile(java.io.File modelFile, java.lang.String text, java.lang.String voiceName, float speed, float pitch) {
        return null;
    }
    
    private final void playAudioFile(java.io.File file) {
    }
}