package cr.ac.utn.movil

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import model.cap_CapacitacionModel

class cap_AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cap_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*model = cap_CapacitacionModel()

       val btnSave = findViewById<Button>(R.id.cap_btnSave)
        btnSave.setOnClickListener {
            saveCapacitacion()
        }*/

    }

    /*private fun saveCapacitacion() {
        // Aquí capturas los datos ingresados por el usuario en los campos XML
        val courseCode = findViewById<EditText>(R.id.cap_editTextCourseCode).text.toString()
        val description = findViewById<EditText>(R.id.cap_editTextDescription).text.toString()
        val hours = findViewById<EditText>(R.id.cap_editTextHours).text.toString().toIntOrNull() ?: 0
        val date = findViewById<EditText>(R.id.cap_editTextDateTime).text.toString()
        val time = findViewById<EditText>(R.id.cap_editTextTime).text.toString()

        // Crea una instancia de Capacitación (identificador) y guárdala usando cap_CapacitacionModel
        val capacitacion = object : Identifier() {
            override val FullDescription: String
                get() = "$courseCode - $description - $hours hours on $date at $time"
        }
        model.addCapacitacion(capacitacion)
    }*/

}