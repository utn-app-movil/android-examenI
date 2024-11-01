package cr.ac.utn.appmovil.identities

open class vul_Persona : Persona {
    var countryDestination: String = ""
    var flightNumber: String = ""
    var flightDateTime: String = ""

    constructor() : super()

    constructor(
        id: String,
        name: String,
        lastName: String,
        phone: Int,
        email: String,
        address: String,
        country: String,
        countryDestination: String,
        flightNumber: String,
        flightDateTime: String
    ) : super(id, name, lastName, phone, email, address, country) {
        this.countryDestination = countryDestination
        this.flightNumber = flightNumber
        this.flightDateTime = flightDateTime
    }

    override val FullDescription: String
        get() = "$Name $LastName"
}
