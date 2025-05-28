package com.example.room_database

import android.app.Application
import androidx.room.Room
import com.example.room_database.StudentDatabase.StudentDatabase

class MyApplication : Application() {

    companion object {
         lateinit var database: StudentDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            StudentDatabase::class.java,
            StudentDatabase.NAME

        ).fallbackToDestructiveMigration().build()
    }
}