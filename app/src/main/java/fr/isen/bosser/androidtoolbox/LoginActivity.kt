package fr.isen.bosser.androidtoolbox


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val PRIVATE_MODE = 0
    val PREF_NAME = "mindorks-welcome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        if (sharedPref.getBoolean(PREF_NAME, false)) {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }

        validate.setOnClickListener {
            val login = login.text.toString()
            val password = password.text.toString()
            val message = "identifiant ou mot de passe invalide"
            if (login=="admin" && password=="123") {
                startActivity( Intent(this,HomeActivity::class.java) )
                val editor = sharedPref.edit()
                editor.putBoolean(PREF_NAME, true)
                editor.apply()
            }
            else {
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
            }
        }


    }
}
