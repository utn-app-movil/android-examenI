package cr.ac.utn.movil
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class enf_enfermeriaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enf_main)

        btnGuardar.setOnClickListener {
            guardarDatos()
        }
    }

    private fun guardarDatos() {
        val presionArterial = editTextPresionArterial.text.toString()
        val peso = editTextPeso.text.toString()
        val altura = editTextAltura.text.toString()
        val oxigenacion = editTextOxigenacion.text.toString()

        if (validarCampos(presionArterial, peso, altura, oxigenacion)) {
            val mensaje = "Presión Arterial: $presionArterial\nPeso: $peso\nAltura: $altura\nOxigenación: $oxigenacion"
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

            limpiarCampos()
        }
    }

    private fun validarCampos(presionArterial: String, peso: String, altura: String, oxigenacion: String): Boolean {
        return when {
            presionArterial.isEmpty() -> {
                mostrarMensaje("Por favor, ingresa la presión arterial.")
                false
            }
            peso.isEmpty() -> {
                mostrarMensaje("Por favor, ingresa el peso.")
                false
            }
            altura.isEmpty() -> {
                mostrarMensaje("Por favor, ingresa la altura.")
                false
            }
            oxigenacion.isEmpty() -> {
                mostrarMensaje("Por favor, ingresa la oxigenación.")
                false
            }
            !esNumero(peso) -> {
                mostrarMensaje("El peso debe ser un número válido.")
                false
            }
            !esNumero(altura) -> {
                mostrarMensaje("La altura debe ser un número válido.")
                false
            }
            !esNumero(oxigenacion) -> {
                mostrarMensaje("La oxigenación debe ser un número válido.")
                false
            }
            else -> true
        }
    }

    private fun esNumero(texto: String): Boolean {
        return texto.toDoubleOrNull() != null
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun limpiarCampos() {
        editTextPresionArterial.text.clear()
        editTextPeso.text.clear()
        editTextAltura.text.clear()
        editTextOxigenacion.text.clear()
    }
}