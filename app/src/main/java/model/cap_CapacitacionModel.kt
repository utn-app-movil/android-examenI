package model

import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.interfaces.IDBManager
import identities.Identifier

class cap_CapacitacionModel {
    private val dbManager: IDBManager = MemoryManager

    // Métodos para manejar capacitaciones usando MemoryManager sin modificarlo
    fun addCapacitacion(capacitacion: Identifier) {
        dbManager.add(capacitacion)
    }

    fun updateCapacitacion(capacitacion: Identifier) {
        dbManager.update(capacitacion)
    }

    fun removeCapacitacion(id: String) {
        dbManager.remove(id)
    }

    fun getCapacitaciones(): List<Identifier> = dbManager.getAll()

    fun getCapacitacionById(id: String): Identifier? = dbManager.getByid(id)
}