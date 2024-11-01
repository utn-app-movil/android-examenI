package com.example.tuaplicacion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EnfermeriaActivity : AppCompatActivity() {

    private lateinit var editTextPresionArterial: EditText
    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editTextOxigenacion: EditText
    private lateinit var enf_btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enf_activity_nursing_control)

        editTextPresionArterial = findViewById(R.id.editTextPresionArterial)
        editTextPeso = findViewById(R.id.editTextPeso)
        editTextAltura = findViewById(R.id.editTextAltura)
        editTextOxigenacion = findViewById(R.id.editTextOxigenacion)
        var enf_btnGuardarGuardar = this.findViewById(R.id.enf_btnGuardar)

        enf_btnGuardar.setOnClickListener {
            guardarRegistro()
        }
    }

    private fun guardarRegistro() {
        val presionArterial = editTextPresionArterial.text.toString()
        val peso = editTextPeso.text.toString()
        val altura = editTextAltura.text.toString()
        val oxigenacion = editTextOxigenacion.text.toString()
        val fechaHora = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())

        val registro = RegistroEnfermeria(presionArterial, peso, altura, oxigenacion, fechaHora)

        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show()


        editTextPresionArterial.text.clear()
        editTextPeso.text.clear()
        editTextAltura.text.clear()
        editTextOxigenacion.text.clear()
    }
}

