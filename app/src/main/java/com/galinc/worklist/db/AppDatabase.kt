package com.galinc.worklist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.galinc.worklist.db.dao.MainTaskDao
import com.galinc.worklist.db.entities.MainTaskDB


@Database(
    entities = [MainTaskDB::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase:RoomDatabase() {

    abstract fun mainTaskDao():MainTaskDao

    companion object {

        val MIGRATION_1_2 =
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE task ADD COLUMN title TEXT DEFAULT '' NOT NULL")
                    database.execSQL("ALTER TABLE task ADD COLUMN isHeader INTEGER DEFAULT 0 NOT NULL")
                }
            }

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "AppDatabase.db")
                .addMigrations(MIGRATION_1_2)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }

                )
                //.allowMainThreadQueries()
                .build()
        }
    }
}