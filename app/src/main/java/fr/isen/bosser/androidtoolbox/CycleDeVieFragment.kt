package fr.isen.bosser.androidtoolbox

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle_de_vie.*
import kotlinx.android.synthetic.main.fragment_cycle_de_vie.*

class CycleDeVieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cycle_de_vie, container, false)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
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
