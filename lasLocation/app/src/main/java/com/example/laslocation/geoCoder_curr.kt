package com.example.laslocation

import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class geoCoder_curr : AppCompatActivity() {
    lateinit var etAddress: EditText
    lateinit var btFindLtLg: Button
    lateinit var tvlognitude: TextView
    lateinit var tvlatitude: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_coder_curr)

        etAddress = findViewById(R.id.location)
        btFindLtLg = findViewById(R.id.searchLocation)
        tvlognitude = findViewById(R.id.lognitude)
        tvlatitude = findViewById(R.id.latitude)

        btFindLtLg.setOnClickListener {
            if (etAddress.text.toString().isNotEmpty()) {
                getLocaionFromAddress(etAddress.text.toString())
            }
            else {
                Toast.makeText(this, "Invalid Address", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getLocaionFromAddress(location: String) {
        val geocoder = Geocoder(this)
        val list: List<Address> = geocoder.getFromLocationName(location, 5)
        if (list.isNullOrEmpty()) return
        tvlatitude.text = "Latitude : ${list[0].latitude}"
        tvlognitude.text = "Longitude : ${list[0].longitude}"
    }
}