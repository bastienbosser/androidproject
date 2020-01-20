package fr.isen.bosser.androidtoolbox

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
            val message = "tu as cliqué $login"
            Toast.makeText( this@LoginActivity, message, Toast.LENGTH_LONG ).show()
        }
    }

}
