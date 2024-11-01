// EmployeeAdapter.kt
package cr.ac.utn.movil

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(private val employees: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.Nombre)
        val positionTextView: TextView = itemView.findViewById(R.id.Puesto)
        val salaryTextView: TextView = itemView.findViewById(R.id.Salario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.nameTextView.text = employee.name
        holder.positionTextView.text = employee.position
        holder.salaryTextView.text = employee.salary.toString()

        // Hacer clic en el CardView para abrir la pantalla de detalles
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleEmpleados::class.java).apply {
                putExtra("employee", employee)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = employees.size
}
