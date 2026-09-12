package com.priya.app.data.scheduler;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001bJ$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\n2\u0006\u0010\u001f\u001a\u00020\u001aH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u001aH\u0002J\u0010\u0010$\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u001aH\u0002J\u0010\u0010%\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u001aH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006&"}, d2 = {"Lcom/priya/app/data/scheduler/PriyaSchedulerImpl;", "Lcom/priya/app/domain/scheduler/ScheduledTaskScheduler;", "context", "Landroid/content/Context;", "priyaDao", "Lcom/priya/app/data/local/PriyaDao;", "(Landroid/content/Context;Lcom/priya/app/data/local/PriyaDao;)V", "alarmManager", "Landroid/app/AlarmManager;", "cancel", "Lkotlin/Result;", "", "taskId", "", "cancel-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelAlarm", "cancelMatching", "type", "Lcom/priya/app/domain/scheduler/TaskType;", "title", "cancelMatching-0E7RQCE", "(Lcom/priya/app/domain/scheduler/TaskType;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelWork", "listPending", "", "Lcom/priya/app/domain/scheduler/ScheduledTask;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreAfterReboot", "restoreAfterReboot-IoAF18A", "schedule", "task", "schedule-gIAlu-s", "(Lcom/priya/app/domain/scheduler/ScheduledTask;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleAndroid", "", "scheduleDeferredWork", "scheduleExactAlarm", "app_debug"})
public final class PriyaSchedulerImpl implements com.priya.app.domain.scheduler.ScheduledTaskScheduler {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.data.local.PriyaDao priyaDao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.AlarmManager alarmManager = null;
    
    @javax.inject.Inject()
    public PriyaSchedulerImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.priya.app.data.local.PriyaDao priyaDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object listPending(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.priya.app.domain.scheduler.ScheduledTask>> $completion) {
        return null;
    }
    
    private final boolean scheduleAndroid(com.priya.app.domain.scheduler.ScheduledTask task) {
        return false;
    }
    
    private final boolean scheduleDeferredWork(com.priya.app.domain.scheduler.ScheduledTask task) {
        return false;
    }
    
    private final boolean scheduleExactAlarm(com.priya.app.domain.scheduler.ScheduledTask task) {
        return false;
    }
    
    private final void cancelAlarm(java.lang.String taskId) {
    }
    
    private final void cancelWork(java.lang.String taskId) {
    }
}