package com.priya.app.domain.ai;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\b\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0013"}, d2 = {"Lcom/priya/app/domain/ai/AIProvider;", "", "providerType", "Lcom/priya/app/domain/ai/AIProviderType;", "getProviderType", "()Lcom/priya/app/domain/ai/AIProviderType;", "generateText", "Lcom/priya/app/domain/ai/AIResponse;", "request", "Lcom/priya/app/domain/ai/AIRequest;", "(Lcom/priya/app/domain/ai/AIRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isConfigured", "", "isRecoverableFailure", "error", "", "streamText", "Lkotlinx/coroutines/flow/Flow;", "Lcom/priya/app/domain/ai/AIResponseChunk;", "app_debug"})
public abstract interface AIProvider {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.ai.AIProviderType getProviderType();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object generateText(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.ai.AIResponse> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.priya.app.domain.ai.AIResponseChunk> streamText(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request);
    
    public abstract boolean isConfigured();
    
    public abstract boolean isRecoverableFailure(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable error);
}