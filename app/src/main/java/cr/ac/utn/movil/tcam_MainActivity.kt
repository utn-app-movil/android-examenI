package cr.ac.utn.appmovil.activities

import android.os.Bundle
import android.text.InputFilter
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cr.ac.utn.appmovil.identities.Persona
import cr.ac.utn.appmovil.identities.tcam_ExchangeTransaction
import cr.ac.utn.appmovil.models.tcam_ExchangeTransactionModel
import cr.ac.utn.appmovil.util.util
import cr.ac.utn.movil.R
import cr.ac.utn.movil.tcam_Custom_List_Activity
import java.text.SimpleDateFormat
import java.util.*

class tcam_MainActivity : AppCompatActivity() {

    private lateinit var tcam_txtFullName: EditText
    private lateinit var tcam_ExchangeRate: EditText
    private lateinit var tcam_edtAmount: EditText
    private lateinit var tcam_txtDateTime: TextView
    private lateinit var tcam_txtAmountToPay: TextView
    private lateinit var transactionModel: tcam_ExchangeTransactionModel
    private var isEditionMode: Boolean = false

    val EXTRA_MESSAGE_TRANSACTION_ID = "com.blopix.myapplication.contactId"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcam_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización del modelo y campos
        transactionModel = tcam_ExchangeTransactionModel()
        tcam_txtFullName = findViewById(R.id.tcam_txtFullName)
        tcam_ExchangeRate = findViewById(R.id.tcam_ExchangeRate)
        tcam_edtAmount = findViewById(R.id.tcam_edtAmount)
        tcam_txtDateTime = findViewById(R.id.tcam_txtDateTime)
        tcam_txtAmountToPay = findViewById(R.id.tcam_txtAmountToPay)

        setEditTextLimits()

        // Captura y muestra la fecha y hora actual
        val currentDateTime = getCurrentDateTime()
        tcam_txtDateTime.text = currentDateTime

        val transactionInfo = intent.getStringExtra(EXTRA_MESSAGE_TRANSACTION_ID)
        if (!transactionInfo.isNullOrEmpty()) loadTransaction(transactionInfo)

        // Lógica para el botón de calcular
        val tcam_btnCalculate: Button = findViewById(R.id.tcam_btnCalculate)
        tcam_btnCalculate.setOnClickListener {
            saveExchangeTransaction()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.tcam_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_contact -> {
                util.openActivity(this, tcam_MainActivity::class.java, "", "")
                return true
            }

            R.id.menu_viewContactList -> {
                util.openActivity(this, tcam_Custom_List_Activity::class.java, "", "")
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Establece límites de caracteres
    private fun setEditTextLimits() {
        tcam_ExchangeRate.filters = arrayOf(InputFilter.LengthFilter(10))
        tcam_edtAmount.filters = arrayOf(InputFilter.LengthFilter(10))
    }

    // Recupera la fecha y hora actual
    private fun getCurrentDateTime(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

    private fun saveExchangeTransaction() {
        try {
            val fullName = tcam_txtFullName.text.toString().trim()
            val exchangeRateString = tcam_ExchangeRate.text.toString().trim()
            val amountString = tcam_edtAmount.text.toString().trim()

            if (dataValidation(fullName, exchangeRateString, amountString)) {
                val exchangeRate = exchangeRateString.toDouble()
                val amount = amountString.toDouble()
                val currentDateTime = tcam_txtDateTime.text.toString()

                // Crea una nueva instancia de Persona
                val persona = Persona(UUID.randomUUID().toString(), fullName, "", 0, "", "", "")

                // Crea una nueva transacción
                val transactionId = UUID.randomUUID().toString() // Genera un ID único
                val transaction = tcam_ExchangeTransaction(
                    transactionId,
                    persona,
                    currentDateTime,
                    exchangeRate,
                    amount
                )

                // Agrega la transacción al modelo
                transactionModel.addTransaction(transaction)

                // Muestra el monto a pagar
                val totalAmount = exchangeRate * amount
                tcam_txtAmountToPay.text = String.format("Monto a pagar: %.2f", totalAmount)

                Toast.makeText(this, "Transacción guardada exitosamente.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    this,
                    "Por favor, completa todos los campos correctamente.",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Log.e("Error", "Ocurrió un error: ${e.message}", e)
            Toast.makeText(this, "Ocurrió un error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun dataValidation(fullName: String, exchangeRate: String, amount: String): Boolean {
        val isFullNameValid = fullName.isNotEmpty()
        val isExchangeRateValid =
            exchangeRate.toDoubleOrNull() != null && exchangeRate.toDouble() > 0
        val isAmountValid = amount.toDoubleOrNull() != null && amount.toDouble() > 0

        return isFullNameValid && isExchangeRateValid && isAmountValid
    }

    private fun loadTransaction(transactionInfo: String) {
        // Lógica para cargar la transacción, si es necesario
    }
}
