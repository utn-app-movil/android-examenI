package cr.ac.utn.movil

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.identities.vac_VaccinationRecord
import cr.ac.utn.appmovil.util.EXTRA_MESSAGE_ID
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class vac_FormActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtAddress: EditText
    private lateinit var txtCountry: EditText
    private lateinit var txtVaccineType: EditText
    private lateinit var txtVaccinationDate: EditText
    private lateinit var txtVaccinationTime: EditText
    private lateinit var btnSaveVaccination: Button
    private var isEditionMode: Boolean = false
    private var vaccinationId: String? = null

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vac_form)

        txtName = findViewById(R.id.vac_name)
        txtLastName = findViewById(R.id.vac_last_name)
        txtPhone = findViewById(R.id.vac_phone)
        txtEmail = findViewById(R.id.vac_email)
        txtAddress = findViewById(R.id.vac_address)
        txtCountry = findViewById(R.id.vac_country)
        txtVaccineType = findViewById(R.id.vac_vaccine_type)
        txtVaccinationDate = findViewById(R.id.vac_vaccination_date)
        txtVaccinationTime = findViewById(R.id.vac_vaccination_time)
        btnSaveVaccination = findViewById(R.id.vac_btn_save_vaccination)

        btnSaveVaccination.setOnClickListener {
            saveVaccinationRecord()
        }

        setupDateTimePickers()

        vaccinationId = intent.getStringExtra(EXTRA_MESSAGE_ID)
        if (vaccinationId != null) {
            loadVaccinationRecord(vaccinationId!!)
            isEditionMode = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.crud_menu, menu)
        menu?.findItem(R.id.mnu_delete)?.isEnabled = isEditionMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnu_save -> {
                saveVaccinationRecord()
                true
            }
            R.id.mnu_delete -> {
                confirmDeletion()
                true
            }
            R.id.mnu_cancel -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveVaccinationRecord() {
        try {
            val vaccinationRecord = vac_VaccinationRecord(
                id = vaccinationId ?: UUID.randomUUID().toString(),
                name = txtName.text.toString(),
                lastName = txtLastName.text.toString(),
                phone = txtPhone.text.toString().toIntOrNull() ?: 0,
                email = txtEmail.text.toString(),
                address = txtAddress.text.toString(),
                country = txtCountry.text.toString(),
                vaccineType = txtVaccineType.text.toString(),
                vaccinationDate = dateFormat.parse(txtVaccinationDate.text.toString()) ?: Date(),
                vaccinationTime = txtVaccinationTime.text.toString()
            )

            if (validateData(vaccinationRecord)) {
                if (isEditionMode) {
                    confirmUpdate(vaccinationRecord)
                } else {
                    if (isDuplicate(vaccinationRecord)) {
                        showToast(getString(R.string.vaccination_duplicate))
                        return
                    }
                    MemoryManager.add(vaccinationRecord)
                    showToast(getString(R.string.vaccination_saved))
                    finish()
                }
            }
        } catch (e: ParseException) {
            showToast(getString(R.string.invalid_date_format))
        } catch (e: Exception) {
            showToast(e.message ?: getString(R.string.error_generic))
        }
    }

    private fun validateData(record: vac_VaccinationRecord): Boolean {
        return when {
            record.Name.isBlank() -> {
                txtName.error = getString(R.string.error_name)
                showToast(getString(R.string.error_name))
                false
            }
            record.LastName.isBlank() -> {
                txtLastName.error = getString(R.string.error_last_name)
                showToast(getString(R.string.error_last_name))
                false
            }
            !isPhoneValid(record.Phone) -> {
                txtPhone.error = getString(R.string.error_phone)
                showToast(getString(R.string.error_phone))
                false
            }
            !isEmailValid(record.Email) -> {
                txtEmail.error = getString(R.string.error_email)
                showToast(getString(R.string.error_email))
                false
            }
            record.Address.isBlank() -> {
                txtAddress.error = getString(R.string.error_address)
                showToast(getString(R.string.error_address))
                false
            }
            record.Country.isBlank() -> {
                txtCountry.error = getString(R.string.error_country)
                showToast(getString(R.string.error_country))
                false
            }
            record.VaccineType.isBlank() -> {
                txtVaccineType.error = getString(R.string.error_vaccine_type)
                showToast(getString(R.string.error_vaccine_type))
                false
            }
            !isDateValid(record.VaccinationDate) -> {
                txtVaccinationDate.error = getString(R.string.error_vaccination_date)
                showToast(getString(R.string.error_vaccination_date))
                false
            }
            record.VaccinationTime.isBlank() -> {
                txtVaccinationTime.error = getString(R.string.error_vaccination_time)
                showToast(getString(R.string.error_vaccination_time))
                false
            }
            else -> true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun isPhoneValid(phone: Int): Boolean {
        val phoneString = phone.toString()
        return phoneString.length in 8..10
    }

    private fun isDateValid(vaccinationDate: Date): Boolean {
        val currentDate = Date()
        return !vaccinationDate.after(currentDate)
    }

    private fun isDuplicate(record: vac_VaccinationRecord): Boolean {
        val allRecords = MemoryManager.getAll().filterIsInstance<vac_VaccinationRecord>()
        return allRecords.any {
            it.Name == record.Name &&
                    it.LastName == record.LastName &&
                    it.VaccineType == record.VaccineType &&
                    it.VaccinationDate == record.VaccinationDate &&
                    it.VaccinationTime == record.VaccinationTime
        }
    }

    private fun confirmUpdate(record: vac_VaccinationRecord) {
        showConfirmationDialog(
            message = getString(R.string.confirm_update),
            onConfirm = {
                MemoryManager.update(record)
                showToast(getString(R.string.vaccination_updated))
                finish()
            }
        )
    }

    private fun confirmDeletion() {
        vaccinationId?.let {
            showConfirmationDialog(
                message = getString(R.string.confirm_delete),
                onConfirm = {
                    MemoryManager.remove(it)
                    showToast(getString(R.string.vaccination_deleted))
                    finish()
                }
            )
        } ?: showToast(getString(R.string.vaccination_not_found))
    }

    private fun showConfirmationDialog(message: String, onConfirm: () -> Unit) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.confirm_yes) { _, _ -> onConfirm() }
            .setNegativeButton(R.string.confirm_no) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun loadVaccinationRecord(id: String) {
        val record = MemoryManager.getByid(id) as? vac_VaccinationRecord
        if (record != null) {
            txtName.setText(record.Name)
            txtLastName.setText(record.LastName)
            txtPhone.setText(record.Phone.toString())
            txtEmail.setText(record.Email)
            txtAddress.setText(record.Address)
            txtCountry.setText(record.Country)
            txtVaccineType.setText(record.VaccineType)
            txtVaccinationDate.setText(dateFormat.format(record.VaccinationDate))
            txtVaccinationTime.setText(record.VaccinationTime)
            isEditionMode = true
            btnSaveVaccination.text = getString(R.string.update_button_text)
        } else {
            showToast(getString(R.string.vaccination_not_found))
        }
    }

    private fun setupDateTimePickers() {
        txtVaccinationDate.setOnClickListener {
            showDatePickerDialog(txtVaccinationDate)
        }
        txtVaccinationTime.setOnClickListener {
            showTimePickerDialog(txtVaccinationTime)
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                editText.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(
            this,
            { _, hour, minute ->
                val time = String.format(Locale.US, "%02d:%02d", hour, minute)
                editText.setText(time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }
}
