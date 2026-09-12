package com.priya.app.domain.scheduler;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/priya/app/domain/scheduler/ScheduledTaskPolicy;", "", "()V", "DUPLICATE_WINDOW_MS", "", "isDuplicate", "", "existing", "Lcom/priya/app/domain/scheduler/ScheduledTask;", "candidate", "app_debug"})
public final class ScheduledTaskPolicy {
    private static final long DUPLICATE_WINDOW_MS = 60000L;
    @org.jetbrains.annotations.NotNull()
    public static final com.priya.app.domain.scheduler.ScheduledTaskPolicy INSTANCE = null;
    
    private ScheduledTaskPolicy() {
        super();
    }
    
    public final boolean isDuplicate(@org.jetbrains.annotations.NotNull()
    com.priya.app.domain.scheduler.ScheduledTask existing, @org.jetbrains.annotations.NotNull()
    com.priya.app.domain.scheduler.ScheduledTask candidate) {
        return false;
    }
}