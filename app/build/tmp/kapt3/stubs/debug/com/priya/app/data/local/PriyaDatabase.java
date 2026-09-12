package com.priya.app.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/priya/app/data/local/PriyaDatabase;", "Landroidx/room/RoomDatabase;", "()V", "priyaDao", "Lcom/priya/app/data/local/PriyaDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.priya.app.data.local.UserProfileEntity.class, com.priya.app.data.local.ConversationEntity.class, com.priya.app.data.local.MessageEntity.class, com.priya.app.data.local.MemoryEntity.class, com.priya.app.data.local.ReminderEntity.class, com.priya.app.data.local.UserSettingsEntity.class, com.priya.app.data.local.ScheduledTaskEntity.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.priya.app.data.local.MemoryTypeConverters.class})
public abstract class PriyaDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATABASE_NAME = "priya_local.db";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.priya.app.data.local.PriyaDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.priya.app.data.local.PriyaDatabase.Companion Companion = null;
    
    public PriyaDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.priya.app.data.local.PriyaDao priyaDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/priya/app/data/local/PriyaDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "INSTANCE", "Lcom/priya/app/data/local/PriyaDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.priya.app.data.local.PriyaDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}