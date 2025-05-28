package com.example.room_database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_database.Entity.Students
import com.example.room_database.MyApplication
import com.example.room_database.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val repository: StudentRepository =
        StudentRepository(MyApplication.database.getDao())

    val studentList: LiveData<List<Students>> = repository.students

    fun add(name: String, age: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(Students(name = name, age = age))
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudent(id)
        }
    }
    fun update(student: Students) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
        }
    }
}
