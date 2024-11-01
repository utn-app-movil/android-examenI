package cr.ac.utn.movil

import cr.ac.utn.appmovil.identities.Candidate
import cr.ac.utn.appmovil.model.CandidateModel
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class rec_RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rec_register)

        val nameEditText = findViewById<EditText>(R.id.rec_edit_name)
        val lastNameEditText = findViewById<EditText>(R.id.rec_edit_last_name)
        val phoneEditText = findViewById<EditText>(R.id.rec_edit_phone)
        val emailEditText = findViewById<EditText>(R.id.rec_edit_email)
        val addressEditText = findViewById<EditText>(R.id.rec_edit_address)
        val countryEditText = findViewById<EditText>(R.id.rec_edit_country)
        val rolesSpinner = findViewById<Spinner>(R.id.rec_spinner_roles)
        val submitButton = findViewById<Button>(R.id.rec_button_submit)

        val roles = listOf(
            getString(R.string.rec_role_software),
            getString(R.string.rec_role_infrastructure),
            getString(R.string.rec_role_networks),
            getString(R.string.rec_role_security),
            getString(R.string.rec_role_qa)
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rolesSpinner.adapter = adapter

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val country = countryEditText.text.toString().trim()
            val role = rolesSpinner.selectedItem.toString()

            // Validaciones
            if (!validateInputs(name, lastName, phone, email, address, country)) {
                return@setOnClickListener // Detener si hay errores
            }

            // Creación del candidato solo si todas las validaciones pasan
            val candidate = Candidate(
                id = System.currentTimeMillis().toString(),
                name = name,
                lastName = lastName,
                phone = phone.toInt(),
                email = email,
                address = address,
                country = country,
                role = role
            )
            CandidateModel.addCandidate(candidate)
            Toast.makeText(this, getString(R.string.rec_data_saved), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // Función para validar todos los campos de entrada
    private fun validateInputs(
        name: String,
        lastName: String,
        phone: String,
        email: String,
        address: String,
        country: String
    ): Boolean {
        if (name.isEmpty() || lastName.isEmpty() || address.isEmpty() || country.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!isAlphabetic(name)) {
            Toast.makeText(this, "El nombre solo puede contener letras", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!isAlphabetic(lastName)) {
            Toast.makeText(this, "El apellido solo puede contener letras", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!isValidPhone(phone)) {
            Toast.makeText(this, "Número de teléfono inválido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!isAlphabetic(country)) {
            Toast.makeText(this, "El país solo puede contener letras", Toast.LENGTH_SHORT).show()
            return false
        }

        return true // Todos los campos son válidos
    }

    // Validación del número de teléfono (sólo dígitos, mínimo 8 dígitos)
    private fun isValidPhone(phone: String): Boolean {
        return phone.length >= 8 && phone.all { it.isDigit() }
    }

    // Validación del correo electrónico
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Validación para que el campo solo contenga letras y espacios
    private fun isAlphabetic(text: String): Boolean {
        return text.all { it.isLetter() || it.isWhitespace() }
    }
}
