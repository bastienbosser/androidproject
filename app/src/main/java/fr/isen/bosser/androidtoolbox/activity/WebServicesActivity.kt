package fr.isen.bosser.androidtoolbox.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bosser.androidtoolbox.adapter.RecyclerViewAdapterWeb
import fr.isen.bosser.androidtoolbox.otherclass.RandomUser
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)
        jsonParse()

    }

    private fun jsonParse(): RandomUser {
        val mQueue = Volley.newRequestQueue(this)
        var webUsers = RandomUser()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, "https://randomuser.me/api/?inc=name%2Cpicture%2Clocation%2Cemail&noinfo=&nat=fr&format=pretty&results=10", null,
            Response.Listener { response ->
                val mygson = Gson()
                webUsers = mygson.fromJson(response.toString(), RandomUser::class.java)

                recyclerViewWeb.layoutManager = LinearLayoutManager(this)
                recyclerViewWeb.adapter = RecyclerViewAdapterWeb(webUsers,this)

            },
            Response.ErrorListener {
                Log.d("TAG","There was an error")
            }
        )
        mQueue.add(jsonObjectRequest)
        return webUsers
    }
}
