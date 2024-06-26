package com.example.projectanroid.presentation.bottom_navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectanroid.presentation.bottom_navigation.HomeScreen.HomeSrceen
import com.example.projectanroid.presentation.bottom_navigation.cart.CartSrceen
import com.example.projectanroid.presentation.bottom_navigation.history.HistorySrceen
import com.example.projectanroid.presentation.bottom_navigation.profile.ProfileSrceen
import com.example.projectanroid.presentation.bottom_navigation.search.SearchSrceen
import com.example.projectanroid.ui.theme.ProjectAnroidTheme


@Composable
fun NavigationMain() {
    val listScreen = listOf(Screen.Home, Screen.Cart, Screen.search, Screen.history, Screen.Profile)
    val navController = rememberNavController()


    CompositionLocalProvider(value = LocalRippleTheme provides DisabledRippleTheme) {
        Scaffold (
            modifier = Modifier.background(Color.DarkGray),
            bottomBar = {
                BottomNavigation(  backgroundColor = Color.White, modifier = Modifier
                    .height(76.dp)
                    .border(0.2.dp, Color.Black, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    listScreen.forEach{screen ->
                        BottomNavigationItem(selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true, onClick = { navController.navigate(screen.route)}
                            , icon = { Image(painter = painterResource(id = screen.icon), contentDescription = null) }
                            , modifier = Modifier.fillMaxHeight()
                            , unselectedContentColor = Color.Transparent
                            , label ={ Text(text = stringResource(id = screen.resourceId), fontSize = 12.sp
                                , modifier = Modifier.padding(bottom = if(currentDestination?.route == screen.route) 20.dp else 0.dp))}
                        )

                    }
                }
            }

        ){
            NavHost(navController = navController, startDestination = Screen.Home.route, Modifier.padding(it) ){
                composable(Screen.Home.route){
                    HomeSrceen()
                }
                composable(Screen.Profile.route){
                    ProfileSrceen()
                }
                composable(Screen.Cart.route){
                    CartSrceen("Crat")
                }
                composable(Screen.history.route){
                    HistorySrceen("hiss")
                }
                composable(Screen.search.route){
                    SearchSrceen("Search")
                }
            }
        }
    }

}

private object DisabledRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0f)
}

@Preview
@Composable
fun  Preview(){
    NavigationMain()
}