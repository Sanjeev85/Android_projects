package com.example.laslocation

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
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.LocationServices
import org.w3c.dom.Text
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private val LOCATION_PERMISSION_REQ_CODE = 1000
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var btGetLocation: Button
    private lateinit var btOpenMap: Button
    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView
    private lateinit var tvProvider: TextView
    private lateinit var tvCountry: TextView
    private lateinit var tvAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btGetLocation = findViewById(R.id.btGetLocation)
        btOpenMap = findViewById(R.id.btOpenMap)
        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)
        tvProvider = findViewById(R.id.tvProvider)
        tvAddress = findViewById(R.id.tvAddress)
        tvCountry = findViewById(R.id.tvCountry)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        btGetLocation.setOnClickListener {
            getCurrentLocation()
        }
        btOpenMap.setOnClickListener {
            openMap()
        }

    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQ_CODE
            )
            return
        }

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                val geocoder = Geocoder(this, Locale.getDefault())
                val list: List<Address> =
                    geocoder.getFromLocation(31.255992,75.705145, 1)

                latitude = list[0].latitude
                longitude = list[0].longitude

                tvLatitude.text = "Latitude : ${list[0].latitude}"
                tvLongitude.text = "Longitude : ${list[0].longitude}"
                tvProvider.text = "Provider : ${location.provider}"
                tvAddress.text = "Address : ${list[0].getAddressLine(0)}"
                tvCountry.text = "Country : ${list[0].countryName}"
                btOpenMap.visibility = View.VISIBLE

            }
            .addOnFailureListener {
                Toast.makeText(this, "Open google maps to read location", Toast.LENGTH_SHORT).show()
            }

    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                } else {
                    Toast.makeText(this, "GRANT permission to proceed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun openMap(){
        val uri = Uri.parse("geo:${latitude}, ${longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}