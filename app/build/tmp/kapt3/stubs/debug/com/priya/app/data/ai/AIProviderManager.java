package com.priya.app.data.ai;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\b\u001a\u00020\tJ \u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0082@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/priya/app/data/ai/AIProviderManager;", "", "geminiProvider", "Lcom/priya/app/domain/ai/AIProvider;", "openRouterProvider", "(Lcom/priya/app/domain/ai/AIProvider;Lcom/priya/app/domain/ai/AIProvider;)V", "generateText", "Lcom/priya/app/domain/ai/AIResponse;", "request", "Lcom/priya/app/domain/ai/AIRequest;", "(Lcom/priya/app/domain/ai/AIRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sanitizeForLogs", "", "message", "streamText", "Lkotlinx/coroutines/flow/Flow;", "Lcom/priya/app/domain/ai/AIResponseChunk;", "tryProvider", "provider", "(Lcom/priya/app/domain/ai/AIProvider;Lcom/priya/app/domain/ai/AIRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AIProviderManager {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.ai.AIProvider geminiProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.ai.AIProvider openRouterProvider = null;
    
    @javax.inject.Inject()
    public AIProviderManager(@javax.inject.Named(value = "gemini")
    @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIProvider geminiProvider, @javax.inject.Named(value = "openRouter")
    @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIProvider openRouterProvider) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateText(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.ai.AIResponse> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.priya.app.domain.ai.AIResponseChunk> streamText(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request) {
        return null;
    }
    
    private final java.lang.Object tryProvider(com.priya.app.domain.ai.AIProvider provider, com.priya.app.domain.ai.AIRequest request, kotlin.coroutines.Continuation<? super com.priya.app.domain.ai.AIResponse> $completion) {
        return null;
    }
    
    private final java.lang.String sanitizeForLogs(java.lang.String message) {
        return null;
    }
}