package com.priya.app.data.permissions;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/priya/app/data/permissions/AndroidPermissionManager;", "Lcom/priya/app/domain/permissions/PermissionManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "permissionState", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/priya/app/domain/permissions/PermissionType;", "Lcom/priya/app/domain/permissions/PermissionStatus;", "getPermissionState", "()Lkotlinx/coroutines/flow/StateFlow;", "permissionStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "explainWhy", "", "type", "(Lcom/priya/app/domain/permissions/PermissionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasPermission", "", "requestPermission", "shouldRequestAgain", "toStatus", "app_debug"})
public final class AndroidPermissionManager implements com.priya.app.domain.permissions.PermissionManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<com.priya.app.domain.permissions.PermissionType, com.priya.app.domain.permissions.PermissionStatus>> permissionStateFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<com.priya.app.domain.permissions.PermissionType, com.priya.app.domain.permissions.PermissionStatus>> permissionState = null;
    
    @javax.inject.Inject()
    public AndroidPermissionManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<java.util.Map<com.priya.app.domain.permissions.PermissionType, com.priya.app.domain.permissions.PermissionStatus>> getPermissionState() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object hasPermission(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object requestPermission(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object explainWhy(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object shouldRequestAgain(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final com.priya.app.domain.permissions.PermissionStatus toStatus(com.priya.app.domain.permissions.PermissionType type) {
        return null;
    }
}