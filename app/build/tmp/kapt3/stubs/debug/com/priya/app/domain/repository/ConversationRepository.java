package com.priya.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/priya/app/domain/repository/ConversationRepository;", "", "addMessage", "", "conversationId", "", "message", "Lcom/priya/app/domain/model/Message;", "(Ljava/lang/String;Lcom/priya/app/domain/model/Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectLanguage", "Lcom/priya/app/domain/model/LanguagePreference;", "input", "getConversation", "Lcom/priya/app/domain/model/Conversation;", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveConversation", "conversation", "(Lcom/priya/app/domain/model/Conversation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLanguagePreference", "languagePreference", "(Ljava/lang/String;Lcom/priya/app/domain/model/LanguagePreference;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ConversationRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.Conversation> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveConversation(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.Conversation conversation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.Message message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLanguagePreference(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.LanguagePreference languagePreference, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.domain.model.LanguagePreference detectLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String input);
}