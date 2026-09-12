package com.priya.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH&J\b\u0010\u000e\u001a\u00020\fH&J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0004H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/priya/app/domain/repository/PriyaRepository;", "", "assistantStatus", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/priya/app/core/AssistantStatus;", "getAssistantStatus", "()Lkotlinx/coroutines/flow/StateFlow;", "conversation", "", "Lcom/priya/app/domain/model/AssistantMessage;", "getConversation", "addMessage", "", "message", "clearConversation", "setStatus", "status", "app_debug"})
public abstract interface PriyaRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<com.priya.app.core.AssistantStatus> getAssistantStatus();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<java.util.List<com.priya.app.domain.model.AssistantMessage>> getConversation();
    
    public abstract void setStatus(@org.jetbrains.annotations.NotNull()
    com.priya.app.core.AssistantStatus status);
    
    public abstract void addMessage(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.AssistantMessage message);
    
    public abstract void clearConversation();
}