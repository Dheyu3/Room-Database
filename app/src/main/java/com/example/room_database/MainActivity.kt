package com.example.room_database
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_database.Entity.Students
import com.example.room_database.databinding.ActivityMainBinding
import com.example.room_database.viewmodel.StudentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var calender: Calendar
    private lateinit var adapter: StudentAdapter
    private lateinit var binding: ActivityMainBinding
    private var selectedTimestamp: Long? = null

    private val viewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calender = Calendar.getInstance()

        adapter = StudentAdapter(
            emptyList(),
            onEditClick = { student -> onEditClick(student) },
            onDeleteClick = { id -> viewModel.delete(id) }
        )

        binding.studentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.studentRecyclerView.adapter = adapter

        viewModel.studentList.observe(this) { students ->
            adapter.updateList(students)
        }
        binding.add.setOnClickListener {
            val name = binding.editTextText.text.toString()
            val ageText = binding.editTextText2.text.toString()
            val dob = selectedTimestamp

            if (name.isEmpty() && ageText.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else if (ageText.isEmpty()) {
                Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
            }else if (dob == null) {
                Toast.makeText(this, "Please select your Date of Birth", Toast.LENGTH_SHORT).show()
            }
            else {
                val age = ageText.toIntOrNull()
                if (age != null) {
                    val dobstored = selectedTimestamp ?: 0L
                    viewModel.add(name, age, dobstored )
                    binding.editTextText.text.clear()
                    binding.editTextText2.text.clear()
                    binding.Dob.text.clear()
                    selectedTimestamp = null
                }
            }
        }
        binding.Dob.setOnClickListener {
            showDatePicker(binding.Dob)}

    }


//--------------------------------- Update Function ---------------------------------------//


    private fun onEditClick(student: Students) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.edit_student, null)
        val editName = dialogView.findViewById<EditText>(R.id.editName)
        val editAge = dialogView.findViewById<EditText>(R.id.editAge)

        editName.setText(student.name)
        editAge.setText(student.age.toString())
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Student")
            .setView(dialogView)
            .setPositiveButton("Update", null)
            .setNegativeButton("Cancel", null)
            .create()
        dialog.setCancelable(false)
        dialog.setOnShowListener {
            val updateButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            updateButton.setOnClickListener {
                val newNameInput = editName.text.toString().trim()
                val newAgeInput = editAge.text.toString().trim()

                if (newNameInput.isEmpty() && newAgeInput.isEmpty()) {
                    Toast.makeText(this, "Please enter both Name and Age", Toast.LENGTH_SHORT)
                        .show()
                } else if (newNameInput.isEmpty()) {
                    Toast.makeText(this, "Please enter the Name", Toast.LENGTH_SHORT).show()
                } else if (newAgeInput.isEmpty()) {
                    Toast.makeText(this, "Please enter the Age", Toast.LENGTH_SHORT).show()
                }
                else {
                    val updatedStudent = student.copy(
                        name = newNameInput,
                        age = newAgeInput.toInt()


                    )
                    viewModel.update(updatedStudent)

                    dialog.dismiss()
                }
            }
        }
        dialog.show()

    }

    //---------------------------------------------------------End ----------------------------------//

    private fun showDatePicker(editText: EditText) {
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"
            editText.setText(selectedDate)

            val tempCalendar = Calendar.getInstance()
            tempCalendar.set(year, month, day)
            selectedTimestamp = tempCalendar.timeInMillis

        }, year, month, day)
        datePickerDialog.show()

    }
}
