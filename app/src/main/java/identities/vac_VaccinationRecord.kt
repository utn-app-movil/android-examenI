package cr.ac.utn.appmovil.identities

import identities.Identifier
import java.util.Date

class vac_VaccinationRecord : Identifier {

    var Name: String = ""
    var LastName: String = ""
    var Phone: Int = 0
    var Email: String = ""
    var Address: String = ""
    var Country: String = ""
    var VaccineType: String = ""
    var VaccinationDate: Date = Date()
    var VaccinationTime: String = ""

    constructor(
        id: String,
        name: String,
        lastName: String,
        phone: Int,
        email: String,
        address: String,
        country: String,
        vaccineType: String,
        vaccinationDate: Date,
        vaccinationTime: String
    ) {
        this.Id = id
        this.Name = name
        this.LastName = lastName
        this.Phone = phone
        this.Email = email
        this.Address = address
        this.Country = country
        this.VaccineType = vaccineType
        this.VaccinationDate = vaccinationDate
        this.VaccinationTime = vaccinationTime
    }

    override val FullDescription: String
        get() = "$Name $LastName - Vacuna: $VaccineType el $VaccinationDate a las $VaccinationTime"
}
