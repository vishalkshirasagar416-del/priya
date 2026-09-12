package com.priya.app.data.tools;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/priya/app/data/tools/SetAlarmTool;", "Lcom/priya/app/data/tools/BaseTool;", "context", "Landroid/content/Context;", "scheduler", "Lcom/priya/app/domain/scheduler/ScheduledTaskScheduler;", "(Landroid/content/Context;Lcom/priya/app/domain/scheduler/ScheduledTaskScheduler;)V", "execute", "Lcom/priya/app/domain/tools/ToolResult;", "arguments", "", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nextOccurrence", "", "hour", "", "minute", "app_debug"})
public final class SetAlarmTool extends com.priya.app.data.tools.BaseTool {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.priya.app.domain.scheduler.ScheduledTaskScheduler scheduler = null;
    
    @javax.inject.Inject()
    public SetAlarmTool(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.scheduler.ScheduledTaskScheduler scheduler) {
        super(null, null, null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object execute(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> arguments, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.priya.app.domain.tools.ToolResult> $completion) {
        return null;
    }
    
    private final long nextOccurrence(int hour, int minute) {
        return 0L;
    }
}