package cr.ac.utn.appmovil.model
import android.content.Context
import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.identities.Persona
import cr.ac.utn.appmovil.identities.vul_Persona
import cr.ac.utn.appmovil.interfaces.IDBManager
import cr.ac.utn.movil.R
import identities.Identifier

open class vul_PersonaModel(private val context: Context) {
    private val dbManager: IDBManager = MemoryManager

    fun addContact(person: vul_Persona){
        dbManager.add(person)
    }

    fun updateContact(person: vul_Persona){
        dbManager.update(person)
    }

    fun removeContact(id: String){
        val result = dbManager.getByid(id)
        if (result == null) {
            throw Exception(context.getString(R.string.vul_NotFound))
        }
        dbManager.remove(id)
    }

    fun getContacts()= dbManager.getAll()

    fun getContact(id: String): vul_Persona{
        return vul_Persona()
    }

    fun getContactbyName(fullName: String): vul_Persona {
        return vul_Persona()
    }

    fun getContactNames(): List<String> {
        val names = mutableListOf<String>()
        return names.toList()
    }
}