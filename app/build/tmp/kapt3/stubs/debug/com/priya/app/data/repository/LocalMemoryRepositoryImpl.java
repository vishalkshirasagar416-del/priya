package com.priya.app.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0018H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00180\u001aH\u0016J.\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\u001e\u0010\"\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\f\u0010%\u001a\u00020\n*\u00020&H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/priya/app/data/repository/LocalMemoryRepositoryImpl;", "Lcom/priya/app/domain/repository/LocalMemoryRepository;", "dao", "Lcom/priya/app/data/local/PriyaDao;", "context", "Landroid/content/Context;", "(Lcom/priya/app/data/local/PriyaDao;Landroid/content/Context;)V", "secureValueStore", "Lcom/priya/app/data/security/KeystoreKeyProvider;", "addMemory", "Lcom/priya/app/domain/model/MemoryRecord;", "memory", "(Lcom/priya/app/domain/model/MemoryRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "containsSensitiveData", "", "value", "", "deleteMemory", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMemories", "", "observeMemories", "Lkotlinx/coroutines/flow/Flow;", "remember", "type", "Lcom/priya/app/domain/model/MemoryType;", "key", "importance", "", "(Lcom/priya/app/domain/model/MemoryType;Ljava/lang/String;Ljava/lang/String;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rememberPreference", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sanitize", "toDomain", "Lcom/priya/app/data/local/MemoryEntity;", "app_debug"})
public final class LocalMemoryRepositoryImpl implements com.priya.app.domain.repository.LocalMemoryRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.local.PriyaDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.security.KeystoreKeyProvider secureValueStore = null;
    
    @javax.inject.Inject()
    public LocalMemoryRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.priya.app.data.local.PriyaDao dao, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.priya.app.domain.model.MemoryRecord>> observeMemories() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMemories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.priya.app.domain.model.MemoryRecord>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addMemory(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.MemoryRecord memory, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.MemoryRecord> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteMemory(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object remember(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.model.MemoryType type, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value, double importance, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.MemoryRecord> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object rememberPreference(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.model.MemoryRecord> $completion) {
        return null;
    }
    
    private final com.priya.app.domain.model.MemoryRecord sanitize(com.priya.app.domain.model.MemoryRecord memory) {
        return null;
    }
    
    private final boolean containsSensitiveData(java.lang.String value) {
        return false;
    }
    
    private final com.priya.app.domain.model.MemoryRecord toDomain(com.priya.app.data.local.MemoryEntity $this$toDomain) {
        return null;
    }
}