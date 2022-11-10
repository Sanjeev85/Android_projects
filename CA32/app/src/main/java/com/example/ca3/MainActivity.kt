package com.example.ca3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var locSer: FusedLocationProviderClient
    var LOCATION_CODE = 102
    lateinit var tvlongitude: TextView
    lateinit var tvlatitude: TextView
    var lat: Double = 0.0
    var lon: Double = 0.0
    lateinit var provider: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locSer = LocationServices.getFusedLocationProviderClient(this)
        tvlatitude = findViewById(R.id.latitude)
        tvlongitude = findViewById(R.id.longi)
        provider = findViewById(R.id.provider)


        findViewById<Button>(R.id.checkLoc).setOnClickListener {
            findViewById<Button>(R.id.getAddress).visibility = View.GONE
            findViewById<EditText>(R.id.currAdd).visibility = View.GONE
            getLocation()
        }
        findViewById<Button>(R.id.openMp).setOnClickListener {
            openMap(lat, lon)
        }

        findViewById<Button>(R.id.getAddress).setOnClickListener {
            if (findViewById<EditText>(R.id.currAdd).text.toString().isNotEmpty()) {
                findDeatils(findViewById<EditText>(R.id.currAdd).text.toString())
            } else {
                Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_CODE
            )
            return
        }

        locSer.lastLocation.addOnSuccessListener { location ->
            val geocoder = Geocoder(this, Locale.getDefault())
            val list: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)

            lat = list[0].latitude
            lon = list[0].longitude


            tvlongitude.text = "Longitude " + list[0].longitude.toString()
            tvlatitude.text = "Latitude " + list[0].latitude.toString()
            provider.text = "Provider : " + location.provider.toString()
            findViewById<TextView>(R.id.addRess).text = "Address : " + list[0].getAddressLine(0)
            findViewById<Button>(R.id.openMp).visibility = View.VISIBLE

        }
            .addOnFailureListener {
                Toast.makeText(this, "Open GOogle Maps", Toast.LENGTH_SHORT).show()
            }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                } else {
                    Toast.makeText(this, "GRANT permission to proceed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openMap(latitude: Double, longitude: Double) {
        val uri = Uri.parse("geo:${latitude}, ${longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun findDeatils(location: String) {
        val geocoder = Geocoder(this)
        val list: List<Address> = geocoder.getFromLocationName(location, 5)
        if (list.isNullOrEmpty()) return
        tvlatitude.text = "Latitude : ${list[0].latitude}"
        tvlongitude.text = "Longitude : ${list[0].longitude}"
        provider.visibility = View.GONE
        findViewById<TextView>(R.id.addRess).text = "Address : " + list[0].getAddressLine(0)
        findViewById<Button>(R.id.checkLoc).visibility = View.GONE
    }

}