package fr.isen.bosser.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle_de_vie.*
import kotlinx.android.synthetic.main.fragment_cycle_de_vie.*

class CycleDeVie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_de_vie)
        state.text = "starting"
        Log.d( "TAG", "Create")
        val newFragment = CycleDeVieFragment()
        val newFragment1 = SecondFragment()
        val transaction1 = supportFragmentManager.beginTransaction()
        transaction1.replace(R.id.layoutcycle, newFragment)
        transaction1.addToBackStack(null)
        transaction1.commit()


        change.setOnClickListener {

            val transaction = supportFragmentManager.beginTransaction()

            if ( newFragment.isVisible ) {
                transaction.replace(R.id.layoutcycle, newFragment1)
            }
            else {
                transaction.replace(R.id.layoutcycle, newFragment)
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun onRestart() {
        super.onRestart()
        state.text = "Restarting"
        Log.d( "TAG", "Restart")
    }

    override fun onResume() {
        super.onResume()
        state.text = "Resume"
    }

    override fun onStart() {
        super.onStart()
        state.text = "Starting"
    }

    override fun onPause() {
        super.onPause()
        state.text = "Pause"
        Log.d( "TAG", "Pause")
    }

    override fun onStop() {
        super.onStop()
        state.text = "Stop"
        Log.d( "TAG", "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Destroy", Toast.LENGTH_LONG).show()
        Log.d( "TAG", "Destroy")
    }
}
