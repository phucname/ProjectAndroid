package com.example.projectanroid.Permission.data

import android.content.Context
import android.location.LocationManager

class PermissionServicePhoneiilm(val contextPermission: Context): PermissionServicePhone {
    override fun CheckPermissionServicePhone(): Boolean {
        val locationManager = contextPermission.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if(isGpsEnabled || isNetworkEnabled) return true
        else return false
    }
}