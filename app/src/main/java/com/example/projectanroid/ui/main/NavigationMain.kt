package com.example.projectanroid.ui.main

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectanroid.MainAppLication
import com.example.projectanroid.R
import com.example.projectanroid.ui.components.BottomBar
import com.example.projectanroid.ui.components.TopBar
import com.example.projectanroid.ui.main.bottom_navigation.HomeScreen.HomeSrceen
import com.example.projectanroid.ui.main.bottom_navigation.Screen
import com.example.projectanroid.ui.main.bottom_navigation.cart.CartSrceen
import com.example.projectanroid.ui.main.bottom_navigation.history.HistorySrceen
import com.example.projectanroid.ui.main.bottom_navigation.search.SearchSrceen
import com.example.projectanroid.ui.detail.AddFood
import com.example.projectanroid.ui.main.Loading.Loading
import com.example.projectanroid.utils.DisabledRippleTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavigationMain(appLication: Context) {
    val navController = rememberNavController()
    CompositionLocalProvider(value = LocalRippleTheme provides DisabledRippleTheme)//Dây là một CompositionLocal có nhiệm vụ quản lý hiệu ứng Ripple trong Compose.
    {
            Scaffold (
                topBar = {
                    TopBar(modifier = Modifier)
                },
                bottomBar = {
                    BottomBar(navController = navController)
                }

            ){
                NavHost(navController = navController, startDestination = Screen.Home.route,
                    Modifier
                        .padding(it)
                        .imePadding()){
                    composable(Screen.Home.route){
                        HomeSrceen()
                    }
                    composable(Screen.Profile.route){
                        AddFood()
                    }
                    composable(Screen.Cart.route){
                        CartSrceen("Crat")
                    }
                    composable(Screen.history.route){
                        HistorySrceen( appLication)
                    }
                    composable(Screen.search.route){
                        SearchSrceen()
                    }

                }
            }
        }

}



@Preview
@Composable
fun  Fetting(){

}