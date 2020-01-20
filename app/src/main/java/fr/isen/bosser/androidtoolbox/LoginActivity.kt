package fr.isen.bosser.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validate.setOnClickListener {
            val login = login.text.toString()
            val password = password.text.toString()
            val message = "identifiant ou mot de passe invalide"
            if (login=="admin" && password=="123") {
                startActivity( Intent(this,HomeActivity::class.java) )
            }
            else {
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
