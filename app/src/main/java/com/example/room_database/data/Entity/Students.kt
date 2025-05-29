package com.example.room_database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Students(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,

    @ColumnInfo(name = "Student Name")
    val name: String,

    @ColumnInfo(name = "Student Age")
    val age: Int,

    @ColumnInfo(name = "Student Date of Birth")
    val dob: Long = 0L
)
