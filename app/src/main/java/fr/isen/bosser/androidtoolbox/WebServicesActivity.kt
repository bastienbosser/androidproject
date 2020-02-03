package fr.isen.bosser.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)

        val webUsers = intent.getParcelableArrayListExtra<WebUser>("webUsers")

        recyclerViewListWebServices.layoutManager = LinearLayoutManager(this)
        recyclerViewListWebServices.adapter = RecyclerViewAdapterWebServices(webUsers,this)
    }
}

@Parcelize
class WebUser(
    var nameWeb: String = "",
    var address: String = "",
    var email: String = "",
    var image: String = ""
): Parcelable