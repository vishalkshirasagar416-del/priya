package com.priya.app.domain.permissions;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\fR$\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lcom/priya/app/domain/permissions/PermissionManager;", "", "permissionState", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/priya/app/domain/permissions/PermissionType;", "Lcom/priya/app/domain/permissions/PermissionStatus;", "getPermissionState", "()Lkotlinx/coroutines/flow/StateFlow;", "explainWhy", "", "type", "(Lcom/priya/app/domain/permissions/PermissionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasPermission", "", "requestPermission", "shouldRequestAgain", "app_debug"})
public abstract interface PermissionManager {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<java.util.Map<com.priya.app.domain.permissions.PermissionType, com.priya.app.domain.permissions.PermissionStatus>> getPermissionState();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object hasPermission(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object requestPermission(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object explainWhy(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object shouldRequestAgain(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.permissions.PermissionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}