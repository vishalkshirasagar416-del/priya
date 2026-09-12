package com.priya.app.domain.voice;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H&J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rH&J\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH&\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH&\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0012R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\t\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0015"}, d2 = {"Lcom/priya/app/domain/voice/WakeWordDetector;", "", "defaultWakeWords", "", "", "getDefaultWakeWords", "()Ljava/util/List;", "isAvailable", "", "()Z", "detectInAudio", "audioText", "observeWakeWords", "Lkotlinx/coroutines/flow/Flow;", "startListening", "Lkotlin/Result;", "", "startListening-d1pmJ48", "()Ljava/lang/Object;", "stopListening", "stopListening-d1pmJ48", "app_debug"})
public abstract interface WakeWordDetector {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<java.lang.String> getDefaultWakeWords();
    
    public abstract boolean isAvailable();
    
    public abstract boolean detectInAudio(@org.jetbrains.annotations.NotNull()
    java.lang.String audioText);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> observeWakeWords();
}