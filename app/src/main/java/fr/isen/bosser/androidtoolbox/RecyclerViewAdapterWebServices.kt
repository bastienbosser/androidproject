package fr.isen.bosser.androidtoolbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_web_services_cell.view.*

class RecyclerViewAdapterWebServices(private val users : ArrayList<WebUser>, val context: Context) : RecyclerView.Adapter<RecyclerViewAdapterWebServices.ViewHolderWeb>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderWeb {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.activity_web_services_cell, parent, false)
        return ViewHolderWeb(users,view,context)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolderWeb, position: Int) {
        holder.loadInfo(position)
    }

    class ViewHolderWeb (private val webUsers: ArrayList<WebUser>, view:View, val context: Context): RecyclerView.ViewHolder(view){
        private val name: TextView = view.nameWeb
        private val image: ImageView = view.pictureWeb
        private val address: TextView = view.addressWeb
        private val email: TextView = view.emailWeb


        fun loadInfo(index: Int){
            Glide.with(context)
                .load(webUsers[index].image)
                .apply(RequestOptions.circleCropTransform())
                .into(image)
            name.text =webUsers[index].nameWeb
            email.text =webUsers[index].email
            address.text =webUsers[index].address

        }

    }




}