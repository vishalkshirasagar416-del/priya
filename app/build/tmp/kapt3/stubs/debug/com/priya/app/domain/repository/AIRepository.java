package com.priya.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\n"}, d2 = {"Lcom/priya/app/domain/repository/AIRepository;", "", "generateResponse", "Lcom/priya/app/domain/ai/AIResponse;", "request", "Lcom/priya/app/domain/ai/AIRequest;", "(Lcom/priya/app/domain/ai/AIRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "streamResponse", "Lkotlinx/coroutines/flow/Flow;", "Lcom/priya/app/domain/ai/AIResponseChunk;", "app_debug"})
public abstract interface AIRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object generateResponse(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.ai.AIResponse> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.priya.app.domain.ai.AIResponseChunk> streamResponse(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request);
}