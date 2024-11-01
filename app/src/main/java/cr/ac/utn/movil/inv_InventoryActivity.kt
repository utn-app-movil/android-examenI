package cr.ac.utn.movil

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.appmovil.data.inv_InventoryManager
import cr.ac.utn.appmovil.model.inv_InventoryEntry
import kotlinx.android.synthetic.main.activity_inv_inventory.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class inv_InventoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inv_inventory)

        btnAddEntry.setOnClickListener {
            val personId = etPersonId.text.toString()
            val personName = etPersonName.text.toString()
            val personEmail = etPersonEmail.text.toString()
            val productCode = etProductCode.text.toString()
            val productName = etProductName.text.toString()
            val productQuantity = etProductQuantity.text.toString().toIntOrNull()
            val providerName = etProviderName.text.toString()
            val unitCost = etUnitCost.text.toString().toDoubleOrNull()

            if (personId.isEmpty() || personName.isEmpty() || personEmail.isEmpty() ||
                productCode.isEmpty() || productName.isEmpty() || productQuantity == null ||
                providerName.isEmpty() || unitCost == null) {
                Toast.makeText(this, "Todos los campos deben ser completados correctamente", Toast.LENGTH_SHORT).show()
            } else {
                val entryDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

                val newEntry = inv_InventoryEntry(
                    personId = personId,
                    personName = personName,
                    personEmail = personEmail,
                    productCode = productCode,
                    productName = productName,
                    productQuantity = productQuantity,
                    entryDateTime = entryDateTime,
                    providerName = providerName,
                    unitCost = unitCost
                )

                inv_InventoryManager.add(newEntry)
                Toast.makeText(this, "Entrada de inventario registrada", Toast.LENGTH_SHORT).show()
                clearFields()
            }
        }
    }

    private fun clearFields() {
        etPersonId.text.clear()
        etPersonName.text.clear()
        etPersonEmail.text.clear()
        etProductCode.text.clear()
        etProductName.text.clear()
        etProductQuantity.text.clear()
        etProviderName.text.clear()
        etUnitCost.text.clear()
    }
}
