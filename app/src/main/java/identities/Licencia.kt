
import identities.Identifier
import java.util.Date

class Licencia(override val FullDescription: String) : Identifier() {
    var tipoLicencia: String = "Licencia"
    var codigoDictamen: String = "Codigo"
    var puntajeActual: Int = 0
    var fechaRenovacion: Date = Date()
    var horaRenovacion: String = "Hora de renovacion"
}