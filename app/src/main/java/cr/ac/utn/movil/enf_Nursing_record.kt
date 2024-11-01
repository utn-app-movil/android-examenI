package com.example.tuaplicacion

import java.text.SimpleDateFormat

data class RegistroEnfermeria(
    var presionArterial: String,
    var peso: String,
    var altura: String,
    var oxigenacion: String,
    var fechaHora: String
) {
    fun isValid(): Boolean {
        return presionArterial.isNotEmpty() && peso.isNotEmpty() &&
                altura.isNotEmpty() && oxigenacion.isNotEmpty()
    }

    fun getFormattedFechaHora(): String {
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return format.format(fechaHora)
    }
}

