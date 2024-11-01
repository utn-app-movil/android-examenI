package cr.ac.utn.movil

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.appmovil.data.inv_InventoryManager
import model.inv_Model

class inv_InventoryActivity : AppCompatActivity() {
    private val manager = inv_InventoryManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_entry)

        val personIdField = findViewById<EditText>(R.id.inputPersonId)
        val personNameField = findViewById<EditText>(R.id.inputPersonName)
        val personEmailField = findViewById<EditText>(R.id.inputPersonEmail)
        val productCodeField = findViewById<EditText>(R.id.inputProductCode)
        val productNameField = findViewById<EditText>(R.id.inputProductName)
        val productQuantityField = findViewById<EditText>(R.id.inputProductQuantity)
        val providerNameField = findViewById<EditText>(R.id.inputProviderName)
        val unitCostField = findViewById<EditText>(R.id.inputUnitCost)
        val saveButton = findViewById<Button>(R.id.buttonSaveEntry)

        saveButton.setOnClickListener {
            val personId = personIdField.text.toString()
            val personName = personNameField.text.toString()
            val personEmail = personEmailField.text.toString()
            val productCode = productCodeField.text.toString()
            val productName = productNameField.text.toString()
            val productQuantity = productQuantityField.text.toString().toIntOrNull() ?: 0
            val providerName = providerNameField.text.toString()
            val unitCost = unitCostField.text.toString().toDoubleOrNull() ?: 0.0

            manager.saveEntry(personId, personName, personEmail, productCode, productName,
                productQuantity, providerName, unitCost)
        }
    }
}
