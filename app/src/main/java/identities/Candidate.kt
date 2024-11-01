package cr.ac.utn.appmovil.identities

import cr.ac.utn.appmovil.identities.Persona

class Candidate(
    id: String,
    name: String,
    lastName: String,
    phone: Int,
    email: String,
    address: String,
    country: String,
    val role: String // Nuevo campo para el rol
) : Persona(id, name, lastName, phone, email, address, country) {

    // Sobrescribimos FullDescription para incluir el rol
    override val FullDescription: String
        get() = "$Name $LastName - Rol: $role"
}
