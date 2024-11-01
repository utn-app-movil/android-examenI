package cr.ac.utn.movil

import cr.ac.utn.appmovil.identities.Candidate
import cr.ac.utn.appmovil.model.CandidateModel
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class rec_ListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var selectedCandidate: Candidate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rec_list)

        listView = findViewById(R.id.rec_list_view)
        registerForContextMenu(listView) // Registrar el menú contextual

        displayCandidates()

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCandidate = CandidateModel.getCandidates()[position]
            val intent = Intent(this, rec_DetailActivity::class.java).apply {
                putExtra("name", selectedCandidate.Name)
                putExtra("lastName", selectedCandidate.LastName)
                putExtra("phone", selectedCandidate.Phone)
                putExtra("email", selectedCandidate.Email)
                putExtra("address", selectedCandidate.Address)
                putExtra("country", selectedCandidate.Country)
                putExtra("role", selectedCandidate.role)
            }
            startActivity(intent)
        }
    }

    // Mostrar la lista de candidatos en el ListView
    private fun displayCandidates() {
        val candidates = CandidateModel.getCandidates()
        val candidateDescriptions = candidates.map { it.FullDescription }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, candidateDescriptions)
        listView.adapter = adapter
    }

    // Crear el menú contextual para editar o eliminar
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.rec_context_menu, menu) // Inflar el menú contextual
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        selectedCandidate = CandidateModel.getCandidates()[info.position] // Guardar el candidato seleccionado
    }

    // Manejar opciones de editar o eliminar en el menú contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                editCandidate(selectedCandidate)
                true
            }
            R.id.action_delete -> {
                deleteCandidate(selectedCandidate)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Función para editar el candidato
    private fun editCandidate(candidate: Candidate?) {
        candidate?.let {
            val intent = Intent(this, rec_RegisterActivity::class.java).apply {
                putExtra("id", it.Id)
                putExtra("name", it.Name)
                putExtra("lastName", it.LastName)
                putExtra("phone", it.Phone)
                putExtra("email", it.Email)
                putExtra("address", it.Address)
                putExtra("country", it.Country)
                putExtra("role", it.role)
            }
            startActivity(intent)
        }
    }

    // Función para eliminar el candidato
    private fun deleteCandidate(candidate: Candidate?) {
        candidate?.let {
            CandidateModel.removeCandidate(it) // Eliminar el candidato
            displayCandidates() // Actualizar la lista
            Toast.makeText(this, "Candidato eliminado", Toast.LENGTH_SHORT).show()
        }
    }
}
