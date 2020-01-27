package fr.isen.bosser.androidtoolbox

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_life_cycle.*



class LifeCycleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        state.text = "starting"
        Log.d( "TAG", "Create")

        val newFragment = LifeCycleFragment()
        val newFragment1 = SecondFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layoutcycle, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        change.setOnClickListener {

            if ( newFragment.isResumed ) {
                Log.d("TAG", "Fragment is resumed")
                supportFragmentManager.beginTransaction().replace(R.id.layoutcycle, newFragment1).commit()
            }
            else {
                Log.d("TAG", "Fragment is resumed")
                supportFragmentManager.beginTransaction().replace(R.id.layoutcycle, newFragment).commit()
            }
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
