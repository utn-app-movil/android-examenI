package cr.ac.utn.movil

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cr.ac.utn.appmovil.util.util


class cap_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cap_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón para abrir cap_AddActivity
        val btnLlamarCapAddActivity: Button = findViewById<Button>(R.id.cap_btnAddTraining)
        btnLlamarCapAddActivity.setOnClickListener(View.OnClickListener { view ->
            util.openActivity(this, cap_AddActivity::class.java)
        })

        // Botón para abrir cap_ListActivity
        val btnLlamarCapListActivity: Button = findViewById<Button>(R.id.cap_btnListTraning)
        btnLlamarCapListActivity.setOnClickListener(View.OnClickListener { view ->
            util.openActivity(this, cap_ListActivity::class.java)
        })
    }
}