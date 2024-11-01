package cr.ac.utn.movil

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cr.ac.utn.appmovil.model.vul_PersonaModel
import cr.ac.utn.appmovil.util.EXTRA_MESSAGE_ID
import cr.ac.utn.appmovil.util.util

class vul_viewListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vul_view_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val personModel = vul_PersonaModel(this)
        val lstContact = findViewById<ListView>(R.id.lstPersonsList)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            personModel.getContactNames())

        lstContact.adapter = adapter

        lstContact.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            val itemValue = lstContact.getItemAtPosition(position) as String
            util.openActivity(this, vul_AddPersonActivity::class.java
                , EXTRA_MESSAGE_ID, itemValue)
        }
    }
}