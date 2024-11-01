package cr.ac.utn.movil

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import identities.far_Medicine

class far_adding_patient : AppCompatActivity() {
    private var medicineList: MutableList<far_Medicine> = mutableListOf()
    private lateinit var txtPrice: EditText
    private lateinit var txtQuantity: EditText
    private lateinit var txtName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_far_adding_patient)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtPrice = findViewById(R.id.far_price)
        txtQuantity = findViewById(R.id.far_quantityMedicine)
        txtName = findViewById(R.id.far_nameMedicine)

        val btnAdding: Button = findViewById<Button>(R.id.far_btn_add)
        btnAdding.setOnClickListener(View.OnClickListener { view ->
            far_addMedicine()
        })
    }

    private fun far_cleanMedicineFields(){
        txtPrice.setText("")
        txtQuantity.setText("")
        txtName.setText("")
    }

    private fun far_addMedicine(){
        val medicine = far_Medicine()
        medicine.name = txtName.text.toString()
        medicine.price = txtPrice.text.toString()?.toInt()!!
        medicine.quantity = txtQuantity.text.toString()?.toInt()!!
        try {
            medicineList.add(medicine)
            far_cleanMedicineFields()
            Toast.makeText(this, R.string.far_success_addMedicine, Toast.LENGTH_LONG).show()
        } catch (err: Exception){
            Toast.makeText(this, err.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun far_addFacture (){

    }
}