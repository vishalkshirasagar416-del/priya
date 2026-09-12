package com.priya.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eH\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000e0\u0010H&\u00a8\u0006\u0011"}, d2 = {"Lcom/priya/app/domain/repository/MemoryRepository;", "", "addMemory", "Lcom/priya/app/domain/model/MemoryRecord;", "memory", "(Lcom/priya/app/domain/model/MemoryRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMemory", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMemories", "", "observeMemories", "Lkotlinx/coroutines/flow/Flow;", "app_debug"})
public abstract interface MemoryRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.priya.app.domain.model.MemoryRecord>> observeMemories();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.priya.app.domain.model.MemoryRecord>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addMemory(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.MemoryRecord memory, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.MemoryRecord> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMemory(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}