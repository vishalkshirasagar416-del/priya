package com.priya.app.data.voice;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0011J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$J.\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00152\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\'\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b)\u0010*J\u001c\u0010+\u001a\b\u0012\u0004\u0012\u00020&0\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010\u0018R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006-"}, d2 = {"Lcom/priya/app/data/voice/PriyaVoiceManager;", "", "elevenLabsTTSProvider", "Lcom/priya/app/data/voice/ElevenLabsTTSProvider;", "kokoroTTSProvider", "Lcom/priya/app/data/voice/KokoroTTSProvider;", "localAndroidTTSProvider", "Lcom/priya/app/data/voice/LocalAndroidTTSProvider;", "voicePreferences", "Lcom/priya/app/data/voice/VoicePreferences;", "kokoroModelManager", "Lcom/priya/app/data/voice/KokoroModelManager;", "(Lcom/priya/app/data/voice/ElevenLabsTTSProvider;Lcom/priya/app/data/voice/KokoroTTSProvider;Lcom/priya/app/data/voice/LocalAndroidTTSProvider;Lcom/priya/app/data/voice/VoicePreferences;Lcom/priya/app/data/voice/KokoroModelManager;)V", "_voiceState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/priya/app/core/AudioState;", "voiceState", "Lkotlinx/coroutines/flow/StateFlow;", "getVoiceState", "()Lkotlinx/coroutines/flow/StateFlow;", "ensureKokoroModel", "Lkotlin/Result;", "Ljava/io/File;", "ensureKokoroModel-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKokoroModelState", "Lcom/priya/app/data/voice/KokoroModelState;", "getPriorityOrder", "", "Lcom/priya/app/domain/voice/TextToSpeechEngine;", "preference", "Lcom/priya/app/domain/voice/VoiceEnginePreference;", "hasKokoroModel", "", "shouldInterruptOn", "text", "", "speak", "", "context", "Lcom/priya/app/domain/voice/VoiceContext;", "speak-0E7RQCE", "(Ljava/lang/String;Lcom/priya/app/domain/voice/VoiceContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "stop-IoAF18A", "app_debug"})
public final class PriyaVoiceManager {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.ElevenLabsTTSProvider elevenLabsTTSProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.KokoroTTSProvider kokoroTTSProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.LocalAndroidTTSProvider localAndroidTTSProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.VoicePreferences voicePreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.KokoroModelManager kokoroModelManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.priya.app.core.AudioState> _voiceState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AudioState> voiceState = null;
    
    @javax.inject.Inject()
    public PriyaVoiceManager(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.ElevenLabsTTSProvider elevenLabsTTSProvider, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.KokoroTTSProvider kokoroTTSProvider, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.LocalAndroidTTSProvider localAndroidTTSProvider, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.VoicePreferences voicePreferences, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.KokoroModelManager kokoroModelManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AudioState> getVoiceState() {
        return null;
    }
    
    public final boolean shouldInterruptOn(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.priya.app.domain.voice.TextToSpeechEngine> getPriorityOrder(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.VoiceEnginePreference preference) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.priya.app.data.voice.KokoroModelState> getKokoroModelState() {
        return null;
    }
    
    public final boolean hasKokoroModel() {
        return false;
    }
}