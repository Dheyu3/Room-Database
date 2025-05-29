package com.example.room_database
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room_database.Entity.Students
import com.example.room_database.databinding.ActivityStudentAdapterBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StudentAdapter(
    private var studentList: List<Students>,
    private val onEditClick: (Students) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(val binding: ActivityStudentAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ActivityStudentAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.binding.textViewName.text = "Name: ${student.name}"
        holder.binding.textViewAge.text = "Age: ${student.age}"
        holder.binding.textViewDob.text = "Dob: ${formatDate(student.dob)}"
        holder.binding.btnEdit.setOnClickListener {
            onEditClick(student)
        }

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(student.id)
        }
    }

    fun updateList(newList: List<Students>) {
        studentList = newList
        notifyDataSetChanged()
    }
    private fun formatDate(timestamp: Long): String {
        return if (timestamp == 0L) {
            "Not Set"
        } else {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}
