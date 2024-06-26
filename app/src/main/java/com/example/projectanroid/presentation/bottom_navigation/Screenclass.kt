package com.example.projectanroid.presentation.bottom_navigation

import androidx.annotation.StringRes
import com.example.projectanroid.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon : Int) {
    object Profile : Screen("profile", R.string.profile, R.drawable.user_01)
    object Home : Screen("home", R.string.home, R.drawable.home_04)
    object  Cart: Screen("crat", R.string.cart, R.drawable.notification_cart)
    object history: Screen("histoey", R.string.history, R.drawable.list)
    object search : Screen("search", R.string.search, R.drawable.icon_2)
}