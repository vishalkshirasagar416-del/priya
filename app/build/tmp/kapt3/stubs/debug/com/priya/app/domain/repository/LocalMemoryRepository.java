package com.priya.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/priya/app/domain/repository/LocalMemoryRepository;", "Lcom/priya/app/domain/repository/MemoryRepository;", "remember", "Lcom/priya/app/domain/model/MemoryRecord;", "type", "Lcom/priya/app/domain/model/MemoryType;", "key", "", "value", "importance", "", "(Lcom/priya/app/domain/model/MemoryType;Ljava/lang/String;Ljava/lang/String;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rememberPreference", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LocalMemoryRepository extends com.priya.app.domain.repository.MemoryRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object remember(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.MemoryType type, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value, double importance, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.MemoryRecord> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object rememberPreference(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.MemoryRecord> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}