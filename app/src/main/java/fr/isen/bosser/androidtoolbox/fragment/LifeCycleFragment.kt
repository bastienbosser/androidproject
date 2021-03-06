package fr.isen.bosser.androidtoolbox.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fr.isen.bosser.androidtoolbox.activity.R
import kotlinx.android.synthetic.main.fragment_life_cycle.*

class LifeCycleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    override fun onPause() {
        super.onPause()
        statefragment.text = "Pause fragment"
        Log.d( "TAG", "Pause")
    }

    override fun onStop() {
        super.onStop()
        statefragment.text = "Stop fragment"
        Log.d( "TAG", "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.context, "Destroy fragment", Toast.LENGTH_LONG).show()
        Log.d( "TAG", "Destroy")
    }
}
