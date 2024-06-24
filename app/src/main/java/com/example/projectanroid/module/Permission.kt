package com.example.projectanroid.module

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import com.example.projectanroid.common.StatusPermession
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


lateinit var fusedLocationClient: FusedLocationProviderClient

class Permission(context: Context) {
    val contextPermission = context
    val valuePermission = MutableStateFlow("")
    val _valuePermission = valuePermission


    fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            contextPermission,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @Composable
    fun CallPermissionLocation() {
        val locationPermissionRequest = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    valuePermission.value = StatusPermession.Fine
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    valuePermission.value = StatusPermession.Auto
                }

                else -> {
                    valuePermission.value = StatusPermession.No
                }
            }
        }
        LaunchedEffect(Unit) {
            if (!isPermissionGranted()) {

                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }

        }
    }

    fun getLocatoin(onchan: (String) -> Unit) {
        val locationManager =
            contextPermission.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        GlobalScope.launch {
            if (isGpsEnabled || isNetworkEnabled) {
                fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(contextPermission)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        try {
                            val geocoder = Geocoder(contextPermission)
                            val addresses =
                                geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
                            val addresslist = addresses!![0]
                            onchan(addresslist.locality)
                            println("location:${addresslist.locality}//${addresslist.adminArea}//${addresslist.countryName}")
                        } catch (ex: NullPointerException) {
                            println("erro: $ex")
                        }
                    }

            } else {
                // GPS và Network location provider đều tắt
                // Bạn có thể yêu cầu người dùng bật vị trípr
                println("on GPS")

            }
        }


    }

}