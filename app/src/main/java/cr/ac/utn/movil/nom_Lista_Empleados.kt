package cr.ac.utn.movil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaEmpleados : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EmployeeAdapter
    private val employeeList = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_empleados)

        recyclerView = findViewById(R.id.Lista_Empleados)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar el adaptador
        adapter = EmployeeAdapter(employeeList)
        recyclerView.adapter = adapter

        // Cargar empleados iniciales
        loadEmployees()
    }

    private fun loadEmployees() {
        // Aquí puedes cargar empleados desde una base de datos o de una lista estática
        employeeList.add(Employee(1, "John Doe", "Manager", 5000.0, "IBAN12345", "Bank A"))
        employeeList.add(Employee(2, "Jane Smith", "Developer", 4000.0, "IBAN67890", "Bank B"))
        adapter.notifyDataSetChanged()
    }
}
