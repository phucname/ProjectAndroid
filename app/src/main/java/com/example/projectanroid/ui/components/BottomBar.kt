package com.example.projectanroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.projectanroid.ui.main.bottom_navigation.Screen

@Composable
fun BottomBar(navController: NavController){
    val listScreen = listOf(Screen.Home, Screen.Cart, Screen.search, Screen.history, Screen.Profile)
    BottomNavigation(  backgroundColor = Color.White, modifier = Modifier
        .height(76.dp)
        .border(
            0.2.dp,
            Color.Black,
            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        )
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
                    , modifier = Modifier.padding(bottom = if(currentDestination?.route == screen.route) 20.dp else 0.dp))
                }
            )

        }
    }
}