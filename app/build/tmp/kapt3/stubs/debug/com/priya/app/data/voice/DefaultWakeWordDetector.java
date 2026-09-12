package com.priya.app.data.voice;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0016J\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\n\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0016"}, d2 = {"Lcom/priya/app/data/voice/DefaultWakeWordDetector;", "Lcom/priya/app/domain/voice/WakeWordDetector;", "()V", "defaultWakeWords", "", "", "getDefaultWakeWords", "()Ljava/util/List;", "isAvailable", "", "()Z", "detectInAudio", "audioText", "observeWakeWords", "Lkotlinx/coroutines/flow/Flow;", "startListening", "Lkotlin/Result;", "", "startListening-d1pmJ48", "()Ljava/lang/Object;", "stopListening", "stopListening-d1pmJ48", "app_debug"})
public final class DefaultWakeWordDetector implements com.priya.app.domain.voice.WakeWordDetector {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> defaultWakeWords = null;
    private final boolean isAvailable = false;
    
    @javax.inject.Inject()
    public DefaultWakeWordDetector() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<java.lang.String> getDefaultWakeWords() {
        return null;
    }
    
    @java.lang.Override()
    public boolean isAvailable() {
        return false;
    }
    
    @java.lang.Override()
    public boolean detectInAudio(@org.jetbrains.annotations.NotNull()
    java.lang.String audioText) {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> observeWakeWords() {
        return null;
    }
}