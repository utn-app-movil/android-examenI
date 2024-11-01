package cr.ac.utn.appmovil.model
import android.content.Context
import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.identities.Persona
import cr.ac.utn.appmovil.identities.vul_Persona
import cr.ac.utn.appmovil.interfaces.IDBManager
import cr.ac.utn.movil.R

open class vul_PersonaModel(private val context: Context) {
    private val dbManager: IDBManager = MemoryManager
    private lateinit var _context: Context

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

    fun getContactByFullName (fullName: String): vul_Persona {
        var result = dbManager.getByFullDescription(fullName)
        if(result == null){
            val message = _context.getString(R.string.vul_NotFound)
            throw Exception(message)
        }else{
            return vul_Persona()
        }
    }

    fun getContactNames(): List<String> {
        val names = mutableListOf<String>()
        dbManager.getAll().forEach{i-> names.add(i.FullDescription)}
        return names.toList()
    }
}