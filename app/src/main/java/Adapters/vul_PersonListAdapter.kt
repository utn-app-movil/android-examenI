package Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import cr.ac.utn.appmovil.identities.Persona
import cr.ac.utn.movil.R

class vul_PersonListAdapter (private val context: Context, private val resource: Int,
                             private val datasource: List<Persona>):
    ArrayAdapter<Persona>(context, resource, datasource){
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater

    override fun getCount(): Int {
        return datasource.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.vul_person_item_list, parent, false)
        val lbFullName = rowView.findViewById(R.id.vul_lbItem_FullName) as TextView
        val lbEmail = rowView.findViewById(R.id.vul_lbItem_Email) as TextView
        val lbPhone = rowView.findViewById(R.id.vul_lbItem_Phone) as TextView
        val imgPhoto = rowView.findViewById(R.id.imgItem_Photo) as ImageView

        val person = datasource[position] as Persona
        lbFullName.text = person.FullDescription
        lbPhone.text = person.Phone.toString()
        lbEmail.text = person.Email

        return rowView

        return super.getView(position, convertView, parent)
    }
}