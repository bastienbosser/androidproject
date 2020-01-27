package fr.isen.bosser.androidtoolbox

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val PRIVATE_MODE = 0
    val PREF_NAME = "mindorks-welcome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        cycle.setOnClickListener {
            startActivity( Intent ( this,LifeCycleActivity::class.java))
        }

        logout.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        save.setOnClickListener {
            startActivity( Intent ( this, FormActivity::class.java))
        }

        permission.setOnClickListener {
            startActivity( Intent(this, InformationActivity::class.java))
        }
    }
}
