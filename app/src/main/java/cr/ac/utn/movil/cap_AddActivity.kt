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
import identities.cap_Capacitacion
import model.cap_CapacitacionModel

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
    private lateinit var txtDateTime: EditText

    private lateinit var capacitacionModel: cap_CapacitacionModel
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
        txtDateTime = findViewById(R.id.cap_txtDateTime)

        capacitacionModel = cap_CapacitacionModel(this)

        // Verifica si se está en modo edición
        val capacitacionId = intent.getStringExtra(EXTRA_MESSAGE_ID)
        if (capacitacionId != null && capacitacionId.isNotEmpty()) loadCapacitacion(capacitacionId)
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
                saveCapacitacion()
                true
            }
            R.id.cap_mnu_delete -> {
                deleteCapacitacion()
                true
            }
            R.id.cap_mnu_cancel -> {
                cleanScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveCapacitacion() {
        try {
            val capacitacion = cap_Capacitacion(
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
                _dateTime = txtDateTime.text.toString()
            )

            if (dataValidation(capacitacion)) {
                if (!isEditionMode)
                    capacitacionModel.addCapacitacion(capacitacion)
                else
                    capacitacionModel.updateCapacitacion(capacitacion)

                cleanScreen()
                Toast.makeText(this, R.string.cap_msgSave, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.cap_msgMissingData, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun dataValidation(capacitacion: cap_Capacitacion): Boolean {
        return capacitacion.Id.isNotEmpty() &&
                capacitacion.name.isNotEmpty() &&
                capacitacion.lastName.isNotEmpty() &&
                capacitacion.phone > 0 &&
                capacitacion.address.isNotEmpty() &&
                capacitacion.email.isNotEmpty() &&
                capacitacion.country.isNotEmpty() &&
                capacitacion.courseCode.isNotEmpty() &&
                capacitacion.description.isNotEmpty() &&
                capacitacion.hours > 0 &&
                capacitacion.dateTime.isNotEmpty()
    }

    private fun deleteCapacitacion() {
        capacitacionModel.removeCapacitacion(txtId.text.toString())
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
        txtDateTime.setText("")
        txtId.isEnabled = true
        isEditionMode = false
        invalidateOptionsMenu()
    }

    private fun loadCapacitacion(id: String) {
        try {
            val capacitacion = capacitacionModel.getCapacitacion(id) as cap_Capacitacion
            txtId.setText(capacitacion.Id)
            txtName.setText(capacitacion.name)
            txtLastName.setText(capacitacion.lastName)
            txtPhone.setText(capacitacion.phone.toString())
            txtEmail.setText(capacitacion.email)
            txtAddress.setText(capacitacion.address)
            txtCountry.setText(capacitacion.country)
            txtCourseCode.setText(capacitacion.courseCode)
            txtDescription.setText(capacitacion.description)
            txtHours.setText(capacitacion.hours.toString())
            txtDateTime.setText(capacitacion.dateTime)
            isEditionMode = true
            txtId.isEnabled = false
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
