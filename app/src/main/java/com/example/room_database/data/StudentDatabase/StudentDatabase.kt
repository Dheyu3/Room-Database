package com.example.room_database.StudentDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.room_database.Doa.StudentDao
import com.example.room_database.Entity.Students
import com.example.room_database.data.converters.Converter


@Database(entities = [Students::class], version = 2)
    @TypeConverters(Converter::class)
 abstract class StudentDatabase: RoomDatabase(){

   companion object{
    const val NAME= "Student_DB"
   }

    abstract fun getDao(): StudentDao
}