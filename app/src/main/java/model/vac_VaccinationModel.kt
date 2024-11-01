package cr.ac.utn.appmovil.model

import cr.ac.utn.appmovil.data.MemoryManager
import cr.ac.utn.appmovil.identities.vac_VaccinationRecord
import cr.ac.utn.appmovil.interfaces.IDBManager

class vac_VaccinationModel {
    private val dbManager: IDBManager = MemoryManager

    fun addVaccinationRecord(record: vac_VaccinationRecord) {
        dbManager.add(record)
    }

    fun updateVaccinationRecord(record: vac_VaccinationRecord) {
        dbManager.update(record)
    }

    fun removeVaccinationRecord(id: String) {
        dbManager.remove(id)
    }

    fun getVaccinationRecords(): List<vac_VaccinationRecord> {
        return dbManager.getAll().filterIsInstance<vac_VaccinationRecord>()
    }

    fun getVaccinationRecordById(id: String): vac_VaccinationRecord? {
        return dbManager.getByid(id) as? vac_VaccinationRecord
    }
}
