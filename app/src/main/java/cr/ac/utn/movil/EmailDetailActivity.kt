package cr.ac.utn.movil

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cr.ac.utn.appmovil.model.ema_EmailModel
import cr.ac.utn.appmovil.identities.ema_Email
import cr.ac.utn.appmovil.util.EXTRA_MESSAGE_ID

class EmailDetailActivity : AppCompatActivity() {
    private lateinit var emailModel: ema_EmailModel
    private lateinit var emailId: String
    private lateinit var txtId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_detail)

        // Inicializar el modelo
        emailModel = ema_EmailModel()

        // Obtener el ID del correo electrónico del Intent
        emailId = intent.getStringExtra(EXTRA_MESSAGE_ID) ?: ""

        // Obtener el correo electrónico por ID
        val email: ema_Email? = emailModel.getEmail(emailId)

        // Mostrar la información en TextViews
        email?.let {
            findViewById<TextView>(R.id.txtDetailTitle).text = it.Title
            findViewById<TextView>(R.id.txtDetailMessage).text = it.Message
            findViewById<TextView>(R.id.txtDetailSendDate).text = it.SendDate
            findViewById<TextView>(R.id.txtDetailCc).text = it.CC
            findViewById<TextView>(R.id.txtDetailCco).text = it.CCO
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.ema_crudmenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnusave -> {

                return true
            }
            R.id.mnudelete -> {
                removeEmail()
                return true
            }
            R.id.mnucancel -> {

                finish() // Cierra la actividad
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    private fun removeEmail() {
        try {
            val emailId = txtId.text.toString()
            if (emailId.isNotEmpty()) {
                emailModel.removeEmail(emailId)
                Toast.makeText(this, R.string.msgDelete, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.msgEmailIdMissing, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }


}
