package com.example.room_database.Doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_database.Entity.Students

@Dao
interface StudentDao {

    @Query("SELECT * FROM  STUDENTS ORDER BY ID DESC")
    fun getStudent(): LiveData<List<Students>>

    @Insert
    suspend fun addStudent(students: Students)

    @Query("Delete from Students where id = :id")
    suspend fun deleteStudent(id:Int)

    @Update
    suspend fun updateStudent(student: Students)

}