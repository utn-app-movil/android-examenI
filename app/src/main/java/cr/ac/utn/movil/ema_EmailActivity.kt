package cr.ac.utn.movil

import cr.ac.utn.appmovil.identities.ema_Email
import cr.ac.utn.appmovil.model.ema_EmailModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ema_EmailActivity : AppCompatActivity() {
    private lateinit var txtId: EditText
    private lateinit var txtSubject: EditText
    private lateinit var txtMessage: EditText
    private lateinit var txtSendDate: EditText
    private lateinit var txtCc: EditText
    private lateinit var txtBcc: EditText
    private lateinit var emailModel: ema_EmailModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ema_email)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de campos de entrada
        emailModel = ema_EmailModel()
        txtId = findViewById(R.id.ema_idperson)
        txtSubject = findViewById(R.id.ema_sub)
        txtMessage = findViewById(R.id.ema_mess)
        txtSendDate = findViewById(R.id.ema_date)
        txtCc = findViewById(R.id.ema_cc)
        txtBcc = findViewById(R.id.ema_cco)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.ema_crudmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnusave -> {
                saveEmail()
                true
            }
            R.id.mnudelete -> {
                deleteEmail()
                true
            }
            R.id.mnucancel -> {
                cleanScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Método para guardar un email
    private fun saveEmail() {
        try {
            val email = ema_Email()
            email.Id = txtId.text.toString()
            email.Subject = txtSubject.text.toString()
            email.Message = txtMessage.text.toString()
            email.SendDate = txtSendDate.text.toString()
            email.CC = txtCc.text.toString()
            email.BCC = txtBcc.text.toString()


            emailModel.addEmail(email)
            cleanScreen()
            Toast.makeText(this, R.string.msgSave, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }


    private fun deleteEmail() {
        try {
            val emailId = txtId.text.toString()
            if (emailId.isNotEmpty()) {
                emailModel.removeEmail(emailId)
                cleanScreen()
                Toast.makeText(this, R.string.msgDelete, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.msgEmailIdMissing, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    // Método para limpiar los campos de entrada
    private fun cleanScreen() {
        txtId.setText("")
        txtSubject.setText("")
        txtMessage.setText("")
        txtSendDate.setText("")
        txtCc.setText("")
        txtBcc.setText("")
    }





}
