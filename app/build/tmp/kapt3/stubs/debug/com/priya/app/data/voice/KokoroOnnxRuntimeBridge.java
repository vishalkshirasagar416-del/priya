package com.priya.app.data.voice;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u0019\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0012"}, d2 = {"Lcom/priya/app/data/voice/KokoroOnnxRuntimeBridge;", "", "modelManager", "Lcom/priya/app/data/voice/KokoroModelManager;", "(Lcom/priya/app/data/voice/KokoroModelManager;)V", "env", "Lai/onnxruntime/OrtEnvironment;", "kotlin.jvm.PlatformType", "session", "Lai/onnxruntime/OrtSession;", "close", "", "initializeOnce", "Lkotlin/Result;", "initializeOnce-d1pmJ48", "()Ljava/lang/Object;", "isReady", "", "app_debug"})
public final class KokoroOnnxRuntimeBridge {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.voice.KokoroModelManager modelManager = null;
    private final ai.onnxruntime.OrtEnvironment env = null;
    @org.jetbrains.annotations.Nullable()
    private ai.onnxruntime.OrtSession session;
    
    @javax.inject.Inject()
    public KokoroOnnxRuntimeBridge(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.voice.KokoroModelManager modelManager) {
        super();
    }
    
    public final boolean isReady() {
        return false;
    }
    
    public final void close() {
    }
}