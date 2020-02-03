package fr.isen.bosser.androidtoolbox.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bosser.androidtoolbox.activity.InformationActivity
import fr.isen.bosser.androidtoolbox.activity.R
import kotlinx.android.synthetic.main.activity_information_cell.view.*

class RecyclerViewAdapter(private val items : ArrayList<InformationActivity.User>, val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.activity_information_cell, parent, false)
        return ViewHolder(
            items,
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.loadInfo(position)
    }

    class ViewHolder (private val contactList: ArrayList<InformationActivity.User>, view:View): RecyclerView.ViewHolder(view){
        val id: TextView = view.idDisplayIdCell
        val name: TextView = view.idDisplayNameCell
        private val phone: TextView = view.idDisplayPhoneCell


        fun loadInfo(index:Int){
            id.text = contactList[index].id
            Log.d("TAG",contactList[index].id)
            name.text = contactList[index].name
            Log.d("TAG",contactList[index].name)
            phone.text = contactList[index].phoneNumber
            Log.d("TAG",contactList[index].phoneNumber)
        }

    }


}