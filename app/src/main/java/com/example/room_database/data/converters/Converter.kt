package com.example.room_database.data.converters

import androidx.room.TypeConverter
import java.sql.Date

class Converter {

    @TypeConverter
    fun fromDate(date: Date): Long{
        return date.time
    }
    @TypeConverter
    fun toDate(time: Long): Date{
        return Date(time)
    }
}