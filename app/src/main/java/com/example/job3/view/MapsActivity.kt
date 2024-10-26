package com.example.job3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.job3.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.job3.databinding.ActivityMapsBinding
import com.example.job3.viewmodel.FirestoreViewModel
import com.google.android.gms.maps.CameraUpdateFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var firestoreViewModel: FirestoreViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        firestoreViewModel?.getAllUsers(this) { users ->
            for (user in users) {
                val userLocation = user.location
                val latLng = if (userLocation.isEmpty() || userLocation == "Don't found any location yet" || userLocation == "Location not available") {
                    LatLng(37.4220936, -122.0839) // Default location
                } else {
                    parseLocation(userLocation)
                }

                // Add a marker at the determined location
                val markerOptions = MarkerOptions().position(latLng).title(user.displayName)
                googleMap.addMarker(markerOptions)
            }

            // Optionally, center the camera on the first marker or a default location
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.4220936, -122.0839), 10f))
        }
    }

    private fun parseLocation(location: String): LatLng {
        val latLngSplit = location.split(", ")
        val latitude = latLngSplit[0].substringAfter("Lat: ").toDouble()
        val longitude = latLngSplit[1].substringAfter("Long: ").toDouble()
        return LatLng(latitude, longitude)

    }
}