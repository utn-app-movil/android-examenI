package cr.ac.utn.movil

import Adapters.cap_CapListAdapter
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cr.ac.utn.appmovil.util.EXTRA_MESSAGE_ID
import cr.ac.utn.appmovil.util.util
import model.cap_CapacitacionModel

class cap_ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cap_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var capacitacionModel = cap_CapacitacionModel(this)
        val lstCustomList = findViewById<ListView>(R.id.Cap_lstCustomList)
        var capacitacionList = capacitacionModel.getCapacitaciones()

        val adapter = cap_CapListAdapter (this, R.layout.capacitacion_item_list, capacitacionList)
        lstCustomList.adapter = adapter

        lstCustomList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val capacitacionId = capacitacionList[position].Id
            util.openActivity(this, cap_AddActivity::class.java, EXTRA_MESSAGE_ID, capacitacionId)
        }
    }
}