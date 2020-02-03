package fr.isen.bosser.androidtoolbox.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val PRIVATE_MODE = 0
    private val PREF_NAME = "mindorks-welcome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cycle.setOnClickListener {
            startActivity( Intent ( this,
                LifeCycleActivity::class.java))
        }

        logout.setOnClickListener {
            logout()
        }

        save.setOnClickListener {
            startActivity( Intent ( this, FormActivity::class.java))
        }

        permission.setOnClickListener {
            startActivity( Intent(this, InformationActivity::class.java))
        }

        webservices.setOnClickListener {
            startActivity( Intent(this, WebServicesActivity::class.java))
        }
    }

    private fun logout() {
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }
}
