package cr.ac.utn.appmovil.model
import android.content.Context
import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.identities.Contact
import cr.ac.utn.appmovil.interfaces.IDBManager
import cr.ac.utn.movil.R

class ContactModel {
    private val dbManager: IDBManager = MemoryManager
    private lateinit var _context: Context

    constructor(context: Context){
        _context= context
    }

    fun addContact(contact: Contact){
        dbManager.add(contact)
    }

    fun updateContact(contact: Contact){
        dbManager.update(contact)
    }

    fun removeContact(id: String){
        val result = dbManager.getByid(id)
        if (result == null)
            throw Exception("No possible to be deleted. The information was not found.")

        dbManager.remove(id)
    }

    fun getContacts()= dbManager.getAll()

    fun getContact(id: String): Contact{
        var result = dbManager.getByid(id) as Contact
        if (result == null){
            val message = "The information was not found."
            throw Exception(message)
        }
        return result
    }

    fun getContactbyName(fullName: String): Contact{
        var result = dbManager.getByFullDescription(fullName) as Contact
        if (result == null){
            val message = "The information was not found."
            throw Exception(message)
        }
        return result
    }

    fun getContactNames(): List<String> {
        val names = mutableListOf<String>()
        dbManager.getAll().forEach{ i-> names.add(i.FullDescription)}
        return names.toList()
    }
}