package com.example.projectanroid.common

import android.Manifest


object Screens {
    val Splash = "SPLASH"
    val OnBoarding = "ONBOARDING"
    val Login = "LOGIN"
    val SetLocation = "SET_LOCATION"
}
object TextContent {
    val contentOnBoarding = "Enjoy Restaurant Quality Meals \n" +
            " at Home"
}
object StatusPermession {
    val No = "NO"
    val Fine = "FINE"
    val Auto = "AUTO"
}
object ManifestPermission{
    val Location = Manifest.permission.ACCESS_FINE_LOCATION
}