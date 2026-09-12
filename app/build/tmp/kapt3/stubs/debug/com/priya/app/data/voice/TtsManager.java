package com.priya.app.data.voice;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\bB\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u00a2\u0006\u0002\u0010\u000bJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nJ\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\rJ.\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0017"}, d2 = {"Lcom/priya/app/data/voice/TtsManager;", "", "enginePreference", "Lcom/priya/app/domain/voice/VoiceEnginePreference;", "elevenLabs", "Lcom/priya/app/domain/voice/TextToSpeechEngine;", "kokoro", "local", "(Lcom/priya/app/domain/voice/VoiceEnginePreference;Lcom/priya/app/domain/voice/TextToSpeechEngine;Lcom/priya/app/domain/voice/TextToSpeechEngine;Lcom/priya/app/domain/voice/TextToSpeechEngine;)V", "engines", "", "(Lcom/priya/app/domain/voice/VoiceEnginePreference;Ljava/util/List;)V", "getPriorityOrder", "", "selectEngineForText", "text", "speak", "Lkotlin/Result;", "", "context", "Lcom/priya/app/domain/voice/VoiceContext;", "speak-0E7RQCE", "(Ljava/lang/String;Lcom/priya/app/domain/voice/VoiceContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TtsManager {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.voice.VoiceEnginePreference enginePreference = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.priya.app.domain.voice.TextToSpeechEngine> engines = null;
    
    public TtsManager(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.VoiceEnginePreference enginePreference, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.priya.app.domain.voice.TextToSpeechEngine> engines) {
        super();
    }
    
    public TtsManager(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.VoiceEnginePreference enginePreference, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.TextToSpeechEngine elevenLabs, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.TextToSpeechEngine kokoro, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.voice.TextToSpeechEngine local) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPriorityOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.priya.app.domain.voice.TextToSpeechEngine selectEngineForText(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
}