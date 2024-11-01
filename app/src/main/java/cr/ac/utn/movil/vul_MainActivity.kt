package cr.ac.utn.movil

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cr.ac.utn.appmovil.util.util

class vul_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vul_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val vul_btnAddPerson = findViewById<Button>(R.id.vul_btnAddPerson)
        vul_btnAddPerson.setOnClickListener(View.OnClickListener {
            util.openActivity(this, vul_AddPersonActivity::class.java)
        })

        val vul_btnViewList = findViewById<Button>(R.id.vul_btnViewList)
        vul_btnViewList.setOnClickListener(View.OnClickListener {
            util.openActivity(this, vul_viewListActivity::class.java)
        })

        val vul_btnCloseDialog = findViewById<Button>(R.id.vul_btnCloseDialog)
        vul_btnCloseDialog.setOnClickListener(View.OnClickListener {
            DisplayDialog()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_AddPerson -> {
                util.openActivity(this, vul_AddPersonActivity::class.java)
                return true
            }

            R.id.menu_viewList -> {
                util.openActivity(this, vul_viewListActivity::class.java)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun DisplayDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(R.string.vul_CloseDialog)
            .setCancelable(false)
            .setPositiveButton(R.string.vul_Yes, DialogInterface.OnClickListener{
                    dialog, id -> finish()
            })
            .setNegativeButton(R.string.vul_No, DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle(R.string.vul_CloseDialogTitle)
        alert.show()
    }
}