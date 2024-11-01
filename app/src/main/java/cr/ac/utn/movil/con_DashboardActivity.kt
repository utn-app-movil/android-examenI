package cr.ac.utn.movil

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.appmovil.data.MemoryManager

class DashboardActivity : AppCompatActivity() {

    private lateinit var contributionsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_dashboard) // Crea este layout

        contributionsTextView = findViewById(R.id.con_contributionsTextView)

        // Carga y muestra las contribuciones
        val contributions = MemoryManager.getAll()
        contributionsTextView.text = contributions.joinToString("\n") { it.FullDescription }
    }
}
