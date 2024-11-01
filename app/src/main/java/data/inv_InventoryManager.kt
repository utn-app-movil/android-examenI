package cr.ac.utn.appmovil.data

import model.inv_Model
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class inv_InventoryManager {

    private val memoryManager = MemoryManager  // Usando MemoryManager como base de datos en memoria

    fun saveEntry(personId: String, personName: String, personEmail: String,
                  productCode: String, productName: String, productQuantity: Int,
                  providerName: String, unitCost: Double) {
        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

        val entry = inv_Model(
            personId = personId,
            personName = personName,
            personEmail = personEmail,
            productCode = productCode,
            productName = productName,
            productQuantity = productQuantity,
            entryDateTime = currentDateTime,
            providerName = providerName,
            unitCost = unitCost
        )

        memoryManager.add(entry)
    }


    fun updateEntry(productCode: String, updatedEntry: inv_Model) {
        val entry = memoryManager.getByFullDescription(updatedEntry.productName + " - " + productCode)
        if (entry != null) {
            memoryManager.update(updatedEntry)
        }
    }

    //obtener todos los ingresos de inventario
    fun getAllEntries(): List<inv_Model> {
        return memoryManager.getAll().filterIsInstance<inv_Model>()
    }

    // Método para buscar un ingreso de inventario por el código del producto
    fun getEntryByCode(productCode: String): inv_Model? {
        return memoryManager.getAll().filterIsInstance<inv_Model>().find { it.productCode == productCode }
    }
}
