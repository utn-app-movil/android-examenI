package Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cr.ac.utn.movil.R
import identities.cap_Capacitacion

class cap_CapListAdapter(
    context: Context,
    resource: Int,
    private val datasource: List<cap_Capacitacion>
) : ArrayAdapter<cap_Capacitacion>(context, resource, datasource) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: inflater.inflate(R.layout.capacitacion_item_list, parent, false)

        // Obtén las referencias de los TextViews en el layout
        val lbFullName = rowView.findViewById<TextView>(R.id.cap_lbItemFullName)
        val lbCourseCode = rowView.findViewById<TextView>(R.id.cap_lbItemCourseCode)
        val lbDateTime = rowView.findViewById<TextView>(R.id.cap_lbItemDateTime)

        // Obtiene el objeto `cap_Capacitacion` en la posición actual
        val capacitacion = datasource[position]

        // Asigna los valores de `fullName`, `courseCode`, y `dateTime`
        lbFullName.text = capacitacion.fullName
        lbCourseCode.text = "Code: ${capacitacion.courseCode}"
        lbDateTime.text = "Date: ${capacitacion.dateTime}"

        return rowView
    }
}