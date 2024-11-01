package cr.ac.utn.movil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cr.ac.utn.appmovil.identities.vac_VaccinationRecord

class vac_VaccinationAdapter(
    private val records: List<vac_VaccinationRecord>,
    private val onItemClick: (vac_VaccinationRecord) -> Unit
) : RecyclerView.Adapter<vac_VaccinationAdapter.VaccinationViewHolder>() {

    class VaccinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtItem_Name)
        val txtVaccineType: TextView = itemView.findViewById(R.id.txtItem_VaccineType)
        val txtVaccinationDate: TextView = itemView.findViewById(R.id.txtItem_VaccinationDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccinationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vac_vaccination_record, parent, false)
        return VaccinationViewHolder(view)
    }

    override fun onBindViewHolder(holder: VaccinationViewHolder, position: Int) {
        val record = records[position]
        holder.txtName.text = "${record.Name} ${record.LastName}"
        holder.txtVaccineType.text = record.VaccineType
        holder.txtVaccinationDate.text = record.VaccinationDate.toString()
        holder.itemView.setOnClickListener { onItemClick(record) }
    }

    override fun getItemCount() = records.size
}
