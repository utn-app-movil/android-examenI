package cr.ac.utn.movil



import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.movil.R.id.main

class DetalleEmpleados : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var positionTextView: TextView
    private lateinit var salaryTextView: TextView
    private lateinit var ibanTextView: TextView
    private lateinit var bankTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nom_detalle_empleados)

        nameTextView = findViewById(R.id.Nombre)
        positionTextView = findViewById(R.id.Puesto)
        salaryTextView = findViewById(R.id.Salario)
        ibanTextView = findViewById(R.id.Cuenta)
        bankTextView = findViewById(R.id.Banco)

        // Obtener el objeto Employee desde el Intent
        val employee = intent.getParcelableExtra<Employee>("employee")

        // Mostrar los detalles del empleado
        employee?.let {
            nameTextView.text = it.name
            positionTextView.text = it.position
            salaryTextView.text = it.salary.toString()
            ibanTextView.text = it.iban
            bankTextView.text = it.bank
        }
    }
}


class nom_Detalle_Empleados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nom_detalle_empleados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}