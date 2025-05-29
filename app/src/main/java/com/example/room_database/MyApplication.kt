package com.example.room_database

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room_database.StudentDatabase.StudentDatabase

class MyApplication : Application() {

    companion object {
         lateinit var database: StudentDatabase
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Students ADD COLUMN `Student Date of Birth` INTEGER NOT NULL DEFAULT 0")
        }
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            StudentDatabase::class.java,
            StudentDatabase.NAME

        ).addMigrations(MIGRATION_1_2).build()
    }
}