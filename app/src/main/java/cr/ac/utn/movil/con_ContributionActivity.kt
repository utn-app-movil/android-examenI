package cr.ac.utn.movil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.appmovil.data.MemoryManager
import identities.Identifier

class con_ContributionActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var totalContributionsEditText: EditText
    private lateinit var dayEditText: EditText
    private lateinit var monthEditText: EditText
    private lateinit var yearEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var openDashboardButton: Button
    private var contributionId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_form)

        // Inicializa las vistas
        nameEditText = findViewById(R.id.con_name)
        totalContributionsEditText = findViewById(R.id.con_TotalContributions)
        dayEditText = findViewById(R.id.con_Day)
        monthEditText = findViewById(R.id.con_Month)
        yearEditText = findViewById(R.id.con_year)
        saveButton = findViewById(R.id.con_SaveContribution_btn)
        openDashboardButton = findViewById(R.id.con_OpenDashboardBotton)

        saveButton.setOnClickListener {
            saveContribution()
        }

        openDashboardButton.setOnClickListener {
            openDashboard()
        }

        // Cargar la contribución si hay un ID proporcionado
        contributionId = intent.getIntExtra("contributionId", -1).takeIf { it != -1 }
        contributionId?.let { loadContribution(it) }
    }

    private fun saveContribution() {
        val name = nameEditText.text.toString().trim()
        val totalContributions = totalContributionsEditText.text.toString().toIntOrNull() ?: 0
        val day = dayEditText.text.toString().toIntOrNull() ?: 1
        val month = monthEditText.text.toString().toIntOrNull() ?: 1
        val year = yearEditText.text.toString().toIntOrNull() ?: 2024


        val contribution = Identifier(
            id = "$name-$day-$month-$year", // ID único basado en los datos
            FullDescription = "$name: $totalContributions contributions on $day/$month/$year"
        )

        // Guardar en MemoryManager
        MemoryManager.add(contribution)
        Toast.makeText(this, "Contribution saved!", Toast.LENGTH_SHORT).show()

        // Ir al dashboard para mostrar las contribuciones
        openDashboard()
    }

    private fun loadContribution(id: Int) {
        // Obteniendo la contribución por ID
        val contribution = MemoryManager.getByid("$id") // Ajusta cómo obtienes el ID si es necesario
        contribution?.let {
            nameEditText.setText(it.Id) // Ajusta según los campos
            totalContributionsEditText.setText(it.FullDescription) // Ajusta según lo que deseas mostrar
            // Aquí deberías configurar también day, month y year si están disponibles
            // Si tienes métodos en el objeto Identifier para obtener día, mes y año, úsalos aquí
        }
    }

    private fun openDashboard() {
        val intent = Intent(this, con_ContributionActivity::class.java) // Asegúrate de que la clase exista
        startActivity(intent)
    }
}
