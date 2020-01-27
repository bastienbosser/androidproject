package fr.isen.bosser.androidtoolbox


import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Level.parse


class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        birthday.setOnClickListener {
            val dpd = DatePickerDialog(
                this@FormActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    birthday.setText("" + day + "/" + (month + 1) + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        saveform.setOnClickListener {
            writeData( name.text.toString(), firstname.text.toString(), birthday.text.toString() )
        }

        readform.setOnClickListener {
            readData()
        }
    }


    private fun writeData(name: String, firstname: String, birthday: String) {
        if (firstname.isNotEmpty() && name.isNotEmpty()) {
            val data = "{'name': '$name', 'firstname': '$firstname', 'birthday': '$birthday'}"

            File(cacheDir.absolutePath + Companion.JSON_FILE).writeText(data)
            Toast.makeText(this@FormActivity, "Sauvegarde des données", Toast.LENGTH_LONG).show()
        }
    }

    private fun readData() {
        val dataJson: String = File(cacheDir.absolutePath + Companion.JSON_FILE).readText()

        if (dataJson.isNotEmpty()) {
            val jsonObject = JSONObject(dataJson)

            val strBirthday: String = jsonObject.optString("birthday")
            val strName: String = jsonObject.optString("name")
            val strFirstname: String = jsonObject.optString("firstname")
            val strAge = getAge(birthday.text.toString())

            AlertDialog.Builder(this@FormActivity )
                .setTitle("Lecture du fichier")
                .setMessage("Nom : $strName \n Prenom : $strFirstname \n Date : $strBirthday \n Age : $strAge")
                .create()
                .show()

        }
    }

    companion object {
        private const val JSON_FILE = "data_user_toolbox.json"
    }

    private fun getAge(date: String): Int {

        var age = 0
        try {
            val date1 = SimpleDateFormat("dd/MM/yyyy").parse(date)
            val now = Calendar.getInstance()
            val dob = Calendar.getInstance()
            dob.time = date1
            if (dob.after(now)) {
                throw IllegalArgumentException("Can't be born in the future")
            }
            val year1 = now.get(Calendar.YEAR)
            val year2 = dob.get(Calendar.YEAR)
            age = year1 - year2
            val month1 = now.get(Calendar.MONTH)
            val month2 = dob.get(Calendar.MONTH)
            if (month2 > month1) {
                age--
            } else if (month1 == month2) {
                val day1 = now.get(Calendar.DAY_OF_MONTH)
                val day2 = dob.get(Calendar.DAY_OF_MONTH)
                if (day2 > day1) {
                    age--
                }
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return age

    }
}




