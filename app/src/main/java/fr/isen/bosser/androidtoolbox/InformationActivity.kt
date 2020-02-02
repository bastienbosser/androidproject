package fr.isen.bosser.androidtoolbox

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_information.*


class InformationActivity : AppCompatActivity() {

    private var contactList = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        myimage.setOnClickListener{
            optionImage()
        }

        checkContacts()
        val sortedList = contactList.sortedWith(compareBy { it.name })

        recyclerViewList.layoutManager = LinearLayoutManager(this)
        recyclerViewList.adapter = RecyclerViewAdapter(ArrayList(sortedList),this)

    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun pickImageFromCamera() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, CAMERA_PICK_CODE)
        }
    }

    private fun optionImage() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Change Image")

        builder.setMessage("Choose between pick image in gallery or in camera")

        builder.setPositiveButton("Gallery"){ _, _ ->
            Toast.makeText(applicationContext,"go to gallery.",Toast.LENGTH_SHORT).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else{
                    pickImageFromGallery()
                }
            }
            else{
                pickImageFromGallery()
            }
        }

        builder.setNegativeButton("Take Picture"){dialog,which ->
            Toast.makeText(applicationContext,"go to camera",Toast.LENGTH_SHORT).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permissions, CAMERA_PICK_CODE)
                }
                else{
                    pickImageFromCamera()
                }
            }
            else{
                pickImageFromCamera()
            }
        }

        builder.setNeutralButton("Cancel"){_,_ ->
            Toast.makeText(applicationContext,"cancel dialog.",Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
        private const val CAMERA_PICK_CODE = 1002
        private const val READ_CONTACTS_CODE = 100
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            CAMERA_PICK_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    pickImageFromCamera()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            READ_CONTACTS_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    checkContacts()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            myimage.setImageURI(data?.data)
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_PICK_CODE){
            myimage.setImageBitmap(data?.extras?.get("data") as Bitmap)
        }
    }

    class User {
        var id:String = ""
        var name:String = ""
        var phoneNumber:String = ""
    }

    private fun checkContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),
                READ_CONTACTS_CODE)
        } else {
            getContacts()
        }
    }

    private fun getContacts(): StringBuilder {
        val builder = StringBuilder()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null)

        cursor?.let {
            while (it.moveToNext()) {
                val user = User()
                user.id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                user.name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                user.phoneNumber = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactList.add(user)
            }
            it.close()
        }
        Log.d("TAG", contactList[0].id)
        return builder
    }


}
