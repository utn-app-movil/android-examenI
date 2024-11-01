package cr.ac.utn.movil

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cr.ac.utn.appmovil.util.EXTRA_MESSAGE_ID
import identities.cap_Training
import model.cap_TrainingModel

class cap_AddActivity : AppCompatActivity() {

    // Elementos de la interfaz para capturar la información de la capacitación
    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtAddress: EditText
    private lateinit var txtCountry: EditText
    private lateinit var txtCourseCode: EditText
    private lateinit var txtDescription: EditText
    private lateinit var txtHours: EditText
    private lateinit var txtDate: EditText

    private lateinit var trainingModel: cap_TrainingModel
    private var isEditionMode: Boolean = false
    private lateinit var menuItemDelete: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cap_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los campos de entrada de datos
        txtId = findViewById(R.id.cap_txtId)
        txtName = findViewById(R.id.cap_txtName)
        txtLastName = findViewById(R.id.cap_txtLastName)
        txtPhone = findViewById(R.id.cap_txtPhone)
        txtEmail = findViewById(R.id.cap_txtEmail)
        txtAddress = findViewById(R.id.cap_txtAddress)
        txtCountry = findViewById(R.id.cap_txtCountry)
        txtCourseCode = findViewById(R.id.cap_txtCourseCode)
        txtDescription = findViewById(R.id.cap_txtDescription)
        txtHours = findViewById(R.id.cap_txtHours)
        txtDate = findViewById(R.id.cap_txtDate)

        trainingModel = cap_TrainingModel(this)

        // Verifica si se está en modo edición
        val trainingId = intent.getStringExtra(EXTRA_MESSAGE_ID)
        if (trainingId != null && trainingId.isNotEmpty()) loadTraining(trainingId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.cap_crud_menu, menu)
        menuItemDelete = menu!!.findItem(R.id.cap_mnu_delete)
        menuItemDelete.isVisible = isEditionMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cap_mnu_save -> {
                savetraning()
                true
            }
            R.id.cap_mnu_delete -> {
                deleteTraining()
                true
            }
            R.id.cap_mnu_cancel -> {
                cleanScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun savetraning() {
        try {
            val training = cap_Training(
                id = txtId.text.toString(),
                _name = txtName.text.toString(),
                _lastName = txtLastName.text.toString(),
                _phone = txtPhone.text.toString().toIntOrNull() ?: 0,
                _email = txtEmail.text.toString(),
                _address = txtAddress.text.toString(),
                _country = txtCountry.text.toString(),
                _courseCode = txtCourseCode.text.toString(),
                _description = txtDescription.text.toString(),
                _hours = txtHours.text.toString().toIntOrNull() ?: 0,
                _dateTime = txtDate.text.toString()
            )

            if (dataValidation(training)) {
                if (!isEditionMode)
                    trainingModel.addTraining(training)
                else
                    trainingModel.updateTraining(training)

                cleanScreen()
                Toast.makeText(this, R.string.cap_msgSave, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.cap_msgMissingData, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun dataValidation(training: cap_Training): Boolean {
        return training.Id.isNotEmpty() &&
                training.name.isNotEmpty() &&
                training.lastName.isNotEmpty() &&
                training.phone > 0 &&
                training.address.isNotEmpty() &&
                training.email.isNotEmpty() &&
                training.country.isNotEmpty() &&
                training.courseCode.isNotEmpty() &&
                training.description.isNotEmpty() &&
                training.hours > 0 &&
                training.dateTime.isNotEmpty()
    }

    private fun deleteTraining() {
        trainingModel.removeTraining(txtId.text.toString())
        Toast.makeText(this, R.string.cap_msgDelete, Toast.LENGTH_LONG).show()
        cleanScreen()
    }

    private fun cleanScreen() {
        txtId.setText("")
        txtName.setText("")
        txtLastName.setText("")
        txtPhone.setText("")
        txtEmail.setText("")
        txtAddress.setText("")
        txtCountry.setText("")
        txtCourseCode.setText("")
        txtDescription.setText("")
        txtHours.setText("")
        txtDate.setText("")
        txtId.isEnabled = true
        isEditionMode = false
        invalidateOptionsMenu()
    }

    private fun loadTraining(id: String) {
        try {
            val training = trainingModel.getTraining(id) as cap_Training
            txtId.setText(training.Id)
            txtName.setText(training.name)
            txtLastName.setText(training.lastName)
            txtPhone.setText(training.phone.toString())
            txtEmail.setText(training.email)
            txtAddress.setText(training.address)
            txtCountry.setText(training.country)
            txtCourseCode.setText(training.courseCode)
            txtDescription.setText(training.description)
            txtHours.setText(training.hours.toString())
            txtDate.setText(training.dateTime)
            isEditionMode = true
            txtId.isEnabled = false
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
