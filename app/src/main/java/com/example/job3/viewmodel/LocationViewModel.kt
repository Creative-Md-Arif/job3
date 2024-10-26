package com.example.job3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnCompleteListener


class LocationViewModel: ViewModel() {
    private var fusedLocationClient: FusedLocationProviderClient? = null

    fun getLastLocation(context: Context,callback: (String) -> Unit) {
        fusedLocationClient?.lastLocation
            ?.addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful && it.result != null) {
                    val lastLocation = it.result
                    val latitude = lastLocation.latitude
                    val longitude = lastLocation.longitude
                    val location = "Lat: $latitude, Long: $longitude"

                    callback(location)
                } else {
                    callback("Location not found")
                }
            })
    }

    fun initializeFusedLocationClient(client: FusedLocationProviderClient) {
        fusedLocationClient = client
    }


}