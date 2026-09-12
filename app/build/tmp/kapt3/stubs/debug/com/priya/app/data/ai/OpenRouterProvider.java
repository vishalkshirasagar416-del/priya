package com.priya.app.data.ai;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J \u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0002J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u001e"}, d2 = {"Lcom/priya/app/data/ai/OpenRouterProvider;", "Lcom/priya/app/domain/ai/AIProvider;", "()V", "providerType", "Lcom/priya/app/domain/ai/AIProviderType;", "getProviderType", "()Lcom/priya/app/domain/ai/AIProviderType;", "chunkText", "", "", "text", "extractOpenRouterText", "json", "generateText", "Lcom/priya/app/domain/ai/AIResponse;", "request", "Lcom/priya/app/domain/ai/AIRequest;", "(Lcom/priya/app/domain/ai/AIRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isConfigured", "", "isRecoverableFailure", "error", "", "postJson", "url", "payload", "apiKey", "streamText", "Lkotlinx/coroutines/flow/Flow;", "Lcom/priya/app/domain/ai/AIResponseChunk;", "app_debug"})
public final class OpenRouterProvider implements com.priya.app.domain.ai.AIProvider {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.ai.AIProviderType providerType = com.priya.app.domain.ai.AIProviderType.OPENROUTER;
    
    @javax.inject.Inject()
    public OpenRouterProvider() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.priya.app.domain.ai.AIProviderType getProviderType() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object generateText(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.ai.AIResponse> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.priya.app.domain.ai.AIResponseChunk> streamText(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.ai.AIRequest request) {
        return null;
    }
    
    @java.lang.Override()
    public boolean isConfigured() {
        return false;
    }
    
    @java.lang.Override()
    public boolean isRecoverableFailure(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable error) {
        return false;
    }
    
    private final java.util.List<java.lang.String> chunkText(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String postJson(java.lang.String url, java.lang.String payload, java.lang.String apiKey) {
        return null;
    }
    
    private final java.lang.String extractOpenRouterText(java.lang.String json) {
        return null;
    }
}