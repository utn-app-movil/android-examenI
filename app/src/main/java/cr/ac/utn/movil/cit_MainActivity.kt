package cr.ac.utn.movil

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.regex.Pattern
import java.util.*

class cit_MainActivity : AppCompatActivity() {
    private lateinit var cit_PatientName: EditText
    private lateinit var cit_PatientContact: EditText
    private lateinit var cit_DoctorName: EditText
    private lateinit var cit_Specialty: EditText
    private lateinit var cit_BtnSchedule: Button
    private lateinit var cit_BtnSelectDate: Button
    private lateinit var cit_BtnSelectTime: Button
    private lateinit var cit_Date: TextView
    private lateinit var cit_Time: TextView

    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cit_main)

        // Initialize fields
        cit_PatientName = findViewById(R.id.cit_PatientName)
        cit_PatientContact = findViewById(R.id.cit_PatientContact)
        cit_DoctorName = findViewById(R.id.cit_DoctorName)
        cit_Specialty = findViewById(R.id.cit_Specialty)
        cit_BtnSchedule = findViewById(R.id.cit_BtnSchedule)
        cit_BtnSelectDate = findViewById(R.id.cit_BtnSelectDate)
        cit_BtnSelectTime = findViewById(R.id.cit_BtnSelectTime)
        cit_Date = findViewById(R.id.cit_Date)
        cit_Time = findViewById(R.id.cit_Time)

        // Date picker
        cit_BtnSelectDate.setOnClickListener {
            showDatePicker()
        }

        // Time picker
        cit_BtnSelectTime.setOnClickListener {
            showTimePicker()
        }

        // Set up the schedule button
        cit_BtnSchedule.setOnClickListener {
            if (validateInputs()) {
                // Logic to schedule the appointment goes here
                Toast.makeText(this, "Appointment scheduled successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            cit_Date.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            cit_Time.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun validateInputs(): Boolean {
        val name = cit_PatientName.text.toString().trim()
        val contact = cit_PatientContact.text.toString().trim()
        val doctor = cit_DoctorName.text.toString().trim()
        val specialty = cit_Specialty.text.toString().trim()

        return when {
            name.isEmpty() -> {
                showToast("Please enter the patient's name.")
                false
            }
            !isValidEmail(contact) -> {
                showToast("Please enter a valid email address.")
                false
            }
            doctor.isEmpty() -> {
                showToast("Please enter the doctor's name.")
                false
            }
            specialty.isEmpty() -> {
                showToast("Please enter the specialty.")
                false
            }
            selectedDate.isEmpty() -> {
                showToast("Please select a date.")
                false
            }
            selectedTime.isEmpty() -> {
                showToast("Please select a time.")
                false
            }
            else -> true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        )
        return emailPattern.matcher(email).matches()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
