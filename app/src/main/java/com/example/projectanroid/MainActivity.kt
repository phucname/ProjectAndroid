package com.example.projectanroid

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectanroid.contanst.Screens
import com.example.projectanroid.ui.main.NavigationMain
import com.example.projectanroid.ui.main.LoginScreen
import com.example.projectanroid.presentation.onboarding.OnboardingScreen
import com.example.projectanroid.ui.main.SetLocationScreen
import com.example.projectanroid.ui.main.SlplashScreen
import com.example.projectanroid.ui.detail.AddFood
import com.example.projectanroid.ui.main.Loading.Loading
import com.example.projectanroid.ui.theme.ProjectAnroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectAnroidTheme {
                // A surface container using the 'background' color from the theme
                    val navController = rememberNavController()
                    NavHost(navController = navController,startDestination = "Main"){
                        composable(route = Screens.Splash){
                            Loading(applicationContext)
                        }
                        composable(route = Screens.OnBoarding){
                            OnboardingScreen(navController)
                        }
                        composable(route = Screens.Login){

                            LoginScreen(navHostController = navController)
                        }
                        composable(route = Screens.SetLocation){

                            SetLocationScreen()
                        }
                        composable(route = "Main"){
                            NavigationMain(applicationContext)
                        }
                        composable(route = "AddFood"){
                            AddFood()
                        }
                    }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectAnroidTheme {
        Greeting("Android")
    }
}