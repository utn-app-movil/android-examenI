package cr.ac.utn.appmovil.data

import cr.ac.utn.appmovil.interfaces.IDBManager
import identities.Identifier

object MemoryManager: IDBManager {
    private var objectList = mutableListOf<Identifier>()

    override fun add(obj: Identifier) {
        objectList.add(obj)
    }

    override fun update(obj: Identifier) {
        remove(obj.Id)
        objectList.add(obj)
    }

    override fun remove(id: String) {
        objectList.removeIf { it.Id.trim() == id.trim() }
    }

    fun remove(obj: Identifier) {
        objectList.remove(obj)
    }

    override fun getAll(): List<Identifier> = objectList.toList()

    override fun getByid(id: String): Identifier? {
        return objectList.find { it.Id == id }
    }

    override fun getByFullDescription(fullDescription: String): Identifier? {
        return objectList.find { it.FullDescription == fullDescription }
    }
}
