package identities

import cr.ac.utn.appmovil.identities.Persona
import identities.Identifier

class cap_Capacitacion(
    id: String,
    val courseCode: String,
    val description: String,
    val hours: Int,
    val dateTime: String,
    val participant: Persona
) : Identifier() {
    init {
        this.Id = id
    }

    override val FullDescription: String
        get() = "$courseCode - $description"
}
