package com.priya.app.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/priya/app/data/repository/DefaultPriyaRepository;", "Lcom/priya/app/domain/repository/PriyaRepository;", "()V", "_assistantStatus", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/priya/app/core/AssistantStatus;", "_conversation", "", "Lcom/priya/app/domain/model/AssistantMessage;", "assistantStatus", "Lkotlinx/coroutines/flow/StateFlow;", "getAssistantStatus", "()Lkotlinx/coroutines/flow/StateFlow;", "conversation", "getConversation", "addMessage", "", "message", "clearConversation", "setStatus", "status", "app_debug"})
public final class DefaultPriyaRepository implements com.priya.app.domain.repository.PriyaRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.priya.app.core.AssistantStatus> _assistantStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.priya.app.domain.model.AssistantMessage>> _conversation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AssistantStatus> assistantStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.priya.app.domain.model.AssistantMessage>> conversation = null;
    
    @javax.inject.Inject()
    public DefaultPriyaRepository() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AssistantStatus> getAssistantStatus() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<java.util.List<com.priya.app.domain.model.AssistantMessage>> getConversation() {
        return null;
    }
    
    @java.lang.Override()
    public void setStatus(@org.jetbrains.annotations.NotNull()
    com.priya.app.core.AssistantStatus status) {
    }
    
    @java.lang.Override()
    public void addMessage(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.AssistantMessage message) {
    }
    
    @java.lang.Override()
    public void clearConversation() {
    }
}