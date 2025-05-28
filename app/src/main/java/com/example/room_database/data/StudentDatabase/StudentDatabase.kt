package com.example.room_database.StudentDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room_database.Doa.StudentDao
import com.example.room_database.Entity.Students


@Database(entities = [Students::class], version = 1)
 abstract class StudentDatabase: RoomDatabase(){

   companion object{
    const val NAME= "Student_DB"
   }

    abstract fun getDao(): StudentDao
}