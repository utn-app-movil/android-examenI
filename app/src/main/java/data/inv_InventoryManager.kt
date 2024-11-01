package cr.ac.utn.appmovil.data

import cr.ac.utn.appmovil.model.inv_InventoryEntry
import identities.Identifier
import cr.ac.utn.appmovil.interfaces.IDBManager

object inv_InventoryManager : IDBManager {
    private val inventoryEntries = mutableListOf<inv_InventoryEntry>()

    override fun add(obj: Identifier) {
        if (obj is inv_InventoryEntry) {
            inventoryEntries.add(obj)
        }
    }

    override fun update(obj: Identifier) {
        if (obj is inv_InventoryEntry) {
            remove(obj.Id)
            inventoryEntries.add(obj)
        }
    }

    override fun remove(id: String) {
        inventoryEntries.removeIf { it.Id == id }
    }

    override fun getAll(): List<Identifier> = inventoryEntries.toList()

    override fun getByid(id: String): Identifier? {
        return inventoryEntries.find { it.Id == id }
    }

    override fun getByFullDescription(fullDescription: String): Identifier? {
        return inventoryEntries.find { it.FullDescription == fullDescription }
    }
}
