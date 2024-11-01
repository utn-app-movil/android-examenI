package cr.ac.utn.movil.model

import Licencia

class LicenciaModel {
    private val licencias = mutableListOf<Licencia>()

    fun agregarLicencia(licencia: Licencia) {
        licencias.add(licencia)
    }

    fun obtenerLicencias(): List<Licencia> {
        return licencias
    }

}
