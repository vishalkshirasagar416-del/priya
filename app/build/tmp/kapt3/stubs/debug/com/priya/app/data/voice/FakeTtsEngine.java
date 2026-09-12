package com.priya.app.data.voice;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J,\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b \u0010\u0019J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0003H\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006#"}, d2 = {"Lcom/priya/app/data/voice/FakeTtsEngine;", "Lcom/priya/app/domain/voice/TextToSpeechEngine;", "name", "", "available", "", "fail", "(Ljava/lang/String;ZZ)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/priya/app/core/AudioState;", "isConfigured", "()Z", "getName", "()Ljava/lang/String;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "shouldInterruptOn", "text", "shutdown", "Lkotlin/Result;", "", "shutdown-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "speak", "context", "Lcom/priya/app/domain/voice/VoiceContext;", "speak-0E7RQCE", "(Ljava/lang/String;Lcom/priya/app/domain/voice/VoiceContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "stop-IoAF18A", "supportsLanguage", "languageCode", "app_debug"})
public final class FakeTtsEngine implements com.priya.app.domain.voice.TextToSpeechEngine {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    private final boolean available = false;
    private final boolean fail = false;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.priya.app.core.AudioState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AudioState> state = null;
    private final boolean isConfigured = false;
    
    public FakeTtsEngine(@org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean available, boolean fail) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
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
    public boolean supportsLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
        return false;
    }
    
    @java.lang.Override()
    public boolean shouldInterruptOn(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return false;
    }
}