package com.example.room_database.repository

import androidx.lifecycle.LiveData
import com.example.room_database.Doa.StudentDao
import com.example.room_database.Entity.Students

class StudentRepository(private val studentDao: StudentDao) {


    val students: LiveData<List<Students>> = studentDao.getStudent()

    suspend fun addStudent(student: Students) {
        studentDao.addStudent(student)
    }

    suspend fun updateStudent(student: Students) {
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(id: Int) {
        studentDao.deleteStudent(id)
    }

}