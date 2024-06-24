package com.example.projectanroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectanroid.common.Screens
import com.example.projectanroid.presentation.login.LoginScreen
import com.example.projectanroid.presentation.onboarding.OnboardingScreen
import com.example.projectanroid.presentation.set_location.SetLocationScreen
import com.example.projectanroid.presentation.splash.SlplashScreen
import com.example.projectanroid.ui.theme.ProjectAnroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectAnroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,startDestination = Screens.SetLocation){
                        composable(route = Screens.Splash){
                            SlplashScreen(navController)
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