package cr.ac.utn.movil

import android.app.AlertDialog
import android.content.Intent
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
import cr.ac.utn.appmovil.identities.vul_Persona
import cr.ac.utn.appmovil.model.vul_PersonaModel
import cr.ac.utn.appmovil.util.EXTRA_MESSAGE_ID

class vul_AddPersonActivity : AppCompatActivity() {

    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtAddress: EditText
    private lateinit var personaModel: vul_PersonaModel
    private var isEditionMode: Boolean = false
    private lateinit var menuitemDelete: MenuItem
    private lateinit var txtOriginCountry: EditText
    private lateinit var txtDestinationCountry: EditText
    private lateinit var txtFlightNumber: EditText
    private lateinit var txtFlightDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vul_add_person)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        personaModel = vul_PersonaModel(this)

        txtId = findViewById(R.id.vul_txtPerson_Id)
        txtName = findViewById(R.id.vul_txtPersonName)
        txtLastName = findViewById(R.id.vul_txtPersonLastName)
        txtPhone = findViewById(R.id.vul_txtPerson_Phone)
        txtEmail = findViewById(R.id.vul_txtPersonEmail)
        txtAddress = findViewById(R.id.vul_txtPersonAddress)
        txtOriginCountry = findViewById(R.id.vul_txtPersonCountry)
        txtDestinationCountry = findViewById(R.id.vul_txtPersonDestinationCountry)
        txtFlightNumber = findViewById(R.id.vul_txtPersonFlighNumber)
        txtFlightDate = findViewById(R.id.vul_txtPersonFlightDate)

        val contactInfo = intent.getStringExtra(EXTRA_MESSAGE_ID)
        if (!contactInfo.isNullOrEmpty()) {
            isEditionMode = true
            loadContact(contactInfo.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.crud_menu, menu)

        menuitemDelete = menu!!.findItem(R.id.mnu_delete)
        menuitemDelete.isVisible = isEditionMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnu_save -> {
                saveContact()
                true
            }

            R.id.mnu_delete -> {
                showDeleteDialog()
                true
            }

            R.id.mnu_cancel -> {
                cleanScreen()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveContact() {
        try {
            val persona = vul_Persona()
            persona.Id = txtId.text.toString()
            persona.Name = txtName.text.toString()
            persona.LastName = txtLastName.text.toString()
            persona.Phone = txtPhone.text.toString().toIntOrNull() ?: -1
            persona.Email = txtEmail.text.toString()
            persona.Address = txtAddress.text.toString()
            persona.Country = txtOriginCountry.text.toString()
            persona.countryDestination = txtDestinationCountry.text.toString()
            persona.flightNumber = txtFlightNumber.text.toString()
            persona.flightDateTime = txtFlightDate.text.toString()

            if (dataValidation(persona)) {
                if (isEditionMode && personaModel.getContact(persona.Id) != null) {
                    showWarning(getString(R.string.vul_ContactExists))
                    return
                }

                if (!isEditionMode) {
                    personaModel.addContact(persona)
                    val intentAddPersontoHome = Intent(this, vul_MainActivity::class.java)
                    startActivity(intentAddPersontoHome)
                } else {
                    personaModel.updateContact(persona)
                    val intentAddPersontoHome = Intent(this, vul_MainActivity::class.java)
                    startActivity(intentAddPersontoHome)
                }
                cleanScreen()
                showWarning(getString(R.string.vul_Succesfully))
            } else {
                showWarning(getString(R.string.vul_Wrong))
            }

        } catch (e: Exception) {
            showWarning("Error: ${e.message}")
        }
    }

    private fun dataValidation(person: vul_Persona): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val costaRicaPhonePattern = "^[2687]\\d{7}$"
        val internationalPhonePattern = "^\\+\\d{1,3}\\d{4,14}$"
        val datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$"

        return when {
            person.Id.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningID))
                false
            }
            person.Name.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningName))
                false
            }
            person.LastName.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningLastName))
                false
            }
            !(person.Phone.toString().matches(costaRicaPhonePattern.toRegex()) ||
                    person.Phone.toString().matches(internationalPhonePattern.toRegex())) -> {
                showWarning(getString(R.string.vul_WarningPhoneNumber))
                false
            }
            !person.Email.matches(emailPattern.toRegex()) -> {
                showWarning(getString(R.string.vul_WarningEmail))
                false
            }
            person.Address.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningAddress))
                false
            }
            person.Country.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningCountry))
                false
            }
            person.countryDestination.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningCountryDestination))
                false
            }
            person.flightNumber.isEmpty() -> {
                showWarning(getString(R.string.vul_WarningFlightNumber))
                false
            }
            !(person.flightDateTime.matches(costaRicaPhonePattern.toRegex()) ||
                    person.flightDateTime.matches(datePattern.toRegex())) -> {
                showWarning(getString(R.string.vul_WarningDateFormat))
                false
            }
            else -> true
        }
    }

    private fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.vul_ASKDelete)
            .setCancelable(false)
            .setPositiveButton(R.string.vul_Yes) { _, _ -> deleteContact() }
            .setNegativeButton(R.string.vul_No) { dialog, _ -> dialog.cancel() }
            .setTitle(R.string.vul_ASKConfirmation)
            .show()
    }

    private fun deleteContact() {

        showWarning(R.string.vul_Succesfully.toString())
    }

    private fun cleanScreen() {
        txtId.setText("")
        txtName.setText("")
        txtLastName.setText("")
        txtPhone.setText("")
        txtEmail.setText("")
        txtAddress.setText("")
        txtOriginCountry.setText("")
        txtDestinationCountry.setText("")
        txtFlightNumber.setText("")
        txtFlightDate.setText("")
        txtId.isEnabled = true
        isEditionMode = false
        invalidateOptionsMenu()
    }

    private fun loadContact(contactInfo: String){
        try{
            val person = personaModel.getContact(contactInfo)
            txtId.setText(person.Id)
            txtName.setText(person.Name)
            txtLastName.setText(person.LastName)
            txtPhone.setText(person.Phone.toString())
            txtEmail.setText(person.Email)
            txtAddress.setText(person.Address)
            txtOriginCountry.setText(person.Country)
            txtDestinationCountry.setText(person.countryDestination)
            txtFlightNumber.setText(person.flightNumber)
            txtFlightDate.setText(person.flightDateTime)
            isEditionMode = true
            txtId.isEnabled = false
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun showWarning(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}