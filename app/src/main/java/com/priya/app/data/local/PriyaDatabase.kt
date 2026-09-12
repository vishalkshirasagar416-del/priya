package com.priya.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        UserProfileEntity::class,
        ConversationEntity::class,
        MessageEntity::class,
        MemoryEntity::class,
        ReminderEntity::class,
        UserSettingsEntity::class,
        ScheduledTaskEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
@TypeConverters(MemoryTypeConverters::class)
abstract class PriyaDatabase : RoomDatabase() {
    abstract fun priyaDao(): PriyaDao

    companion object {
        const val DATABASE_NAME = "priya_local.db"

        @Volatile
        private var INSTANCE: PriyaDatabase? = null

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // No destructive schema changes; retain existing user data.
            }
        }

        fun getInstance(context: Context): PriyaDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PriyaDatabase::class.java,
                    DATABASE_NAME,
                )
                    .addMigrations(MIGRATION_2_3)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
