//package com.example.projectanroid.presentation.set_location
//
//import android.content.Context
//import android.location.Address
//import android.location.Geocoder
//import android.location.Location
//import com.example.projectanroid.Permission.data.PermissionServicePhoneiilm
//import com.example.projectanroid.Permission.data.Permissionllmt
//import com.example.projectanroid.common.ManifestPermission
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import kotlinx.coroutines.flow.MutableStateFlow
//
//lateinit var fusedLocationClient: FusedLocationProviderClient
//
//class SetLocationmodule( context: Context)  {
//    val contextPermission = context
//    val permissionllmt = Permissionllmt(contextPermission)
//    val permissionServicePhone = PermissionServicePhoneiilm(contextPermission).CheckPermissionServicePhone()
//    val location = MutableStateFlow<List<Address>>(emptyList())
//    fun getLocaton(){
//        if (permissionServicePhone){
//            if (permissionllmt.CheckPermission(ManifestPermission.Location)){
//                fusedLocationClient = LocationServices.getFusedLocationProviderClient(contextPermission)
//                fusedLocationClient.lastLocation
//                    .addOnSuccessListener { location: Location? ->
//                        try {
//                            val geocoder = Geocoder(contextPermission)
//                            val addresses = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
//                            val addresslist = addresses!![0]
//                            onchan(addresslist.locality)
//                            println("location:${addresslist.locality}//${addresslist.adminArea}//${addresslist.countryName}")
//                        }catch (ex : NullPointerException){
//                            println("erro: $ex")
//                        } }
//            }
//        }
//
//    }
//}