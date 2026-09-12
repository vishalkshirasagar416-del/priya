package com.priya.app.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0005H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u0018R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/priya/app/data/repository/InMemoryConversationRepository;", "Lcom/priya/app/domain/repository/ConversationRepository;", "()V", "conversations", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/priya/app/domain/model/Conversation;", "addMessage", "", "conversationId", "message", "Lcom/priya/app/domain/model/Message;", "(Ljava/lang/String;Lcom/priya/app/domain/model/Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectLanguage", "Lcom/priya/app/domain/model/LanguagePreference;", "input", "getConversation", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveConversation", "conversation", "(Lcom/priya/app/domain/model/Conversation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLanguagePreference", "languagePreference", "(Ljava/lang/String;Lcom/priya/app/domain/model/LanguagePreference;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class InMemoryConversationRepository implements com.priya.app.domain.repository.ConversationRepository {
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ConcurrentHashMap<java.lang.String, com.priya.app.domain.model.Conversation> conversations = null;
    
    @javax.inject.Inject()
    public InMemoryConversationRepository() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.Conversation> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveConversation(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.Conversation conversation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.Message message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateLanguagePreference(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.LanguagePreference languagePreference, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.priya.app.domain.model.LanguagePreference detectLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return null;
    }
}