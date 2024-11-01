package cr.ac.utn.movil

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import identities.*
import managers.EventManager

class EveMainActivity : AppCompatActivity() {

    private lateinit var editTextStudentId: EditText
    private lateinit var editTextStudentName: EditText
    private lateinit var editTextInstitution: EditText
    private lateinit var editTextEventLocation: EditText
    private lateinit var editTextEventDate: EditText
    private lateinit var editTextEventTime: EditText
    private lateinit var editTextSeatNumber: EditText
    private lateinit var spinnerEventType: Spinner
    private lateinit var buttonAddRecord: Button

    private val eventManager = EventManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextStudentId = findViewById(R.id.editTextStudentId)
        editTextStudentName = findViewById(R.id.editTextStudentName)
        editTextInstitution = findViewById(R.id.editTextInstitution)
        editTextEventLocation = findViewById(R.id.editTextEventLocation)
        editTextEventDate = findViewById(R.id.editTextEventDate)
        editTextEventTime = findViewById(R.id.editTextEventTime)
        editTextSeatNumber = findViewById(R.id.editTextSeatNumber)
        spinnerEventType = findViewById(R.id.spinnerEventType)
        buttonAddRecord = findViewById(R.id.buttonAddRecord)

        val eventTypes = EventType.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eventTypes)
        spinnerEventType.adapter = adapter

        buttonAddRecord.setOnClickListener {
            addAttendanceRecord()
        }
    }

    private fun addAttendanceRecord() {
        // Obtiene los valores de entrada
        val studentId = editTextStudentId.text.toString().trim()
        val studentName = editTextStudentName.text.toString().trim()
        val institution = editTextInstitution.text.toString().trim()
        val eventLocation = editTextEventLocation.text.toString().trim()
        val eventDate = editTextEventDate.text.toString().trim()
        val eventTime = editTextEventTime.text.toString().trim()
        val seatNumber = editTextSeatNumber.text.toString().trim()
        val eventType = spinnerEventType.selectedItem.toString()

        // Valida que no haya campos vacíos
        if (studentId.isEmpty() || studentName.isEmpty() || institution.isEmpty() ||
            eventLocation.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() ||
            seatNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Crea objetos Student y Event
        val student = Student(studentId, studentName, institution)
        val event = Event(eventLocation, eventDate, eventTime, seatNumber, EventType.valueOf(eventType))

        // Agrega el registro de asistencia
        eventManager.addAttendanceRecord(AttendanceRecord(student, event))

        // Muestra un mensaje de éxito
        Toast.makeText(this, "Attendance record added successfully", Toast.LENGTH_SHORT).show()

        // Limpia los campos después de agregar
        clearFields()
    }

    private fun clearFields() {
        editTextStudentId.text.clear()
        editTextStudentName.text.clear()
        editTextInstitution.text.clear()
        editTextEventLocation.text.clear()
        editTextEventDate.text.clear()
        editTextEventTime.text.clear()
        editTextSeatNumber.text.clear()
        spinnerEventType.setSelection(0) // Restablece el spinner a la primera opción
    }
}
