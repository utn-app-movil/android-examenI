package cr.ac.utn.appmovil.model
import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.identities.Contact
import cr.ac.utn.appmovil.interfaces.IDBManager

class ContactModel {
    private val dbManager: IDBManager = MemoryManager

    fun addContact(contact: Contact){

    }

    fun updateContact(contact: Contact){

    }

    fun removeContact(id: String){

    }

    fun getContacts()= dbManager.getAll()

    fun getContact(id: String): Contact{
        return Contact()
    }

    fun getContactbyName(fullName: String): Contact{
       return Contact()
    }

    fun getContactNames(): List<String> {
        val names = mutableListOf<String>()
        return names.toList()
    }
}