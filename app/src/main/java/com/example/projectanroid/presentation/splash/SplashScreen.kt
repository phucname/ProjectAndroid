package com.example.projectanroid.presentation.splash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectanroid.R
import com.example.projectanroid.common.Screens
import kotlinx.coroutines.delay

@Composable
fun SlplashScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.g10_2)
            , contentDescription = null
            , modifier = Modifier.padding(top = 189.dp, bottom = 59.dp))
        Text(text = "Waves Of Food", fontSize = 40.sp, fontWeight = FontWeight.W400
            //, fontFamily = FontFamily(Font(R.font.font_yeonsung_regular))
                )
        Text(text = "Deliever Favorite Food", fontSize = 14.sp, fontWeight = FontWeight.W700
            //, fontFamily = FontFamily(Font(R.font.font_yeonsung_regular)

            , modifier = Modifier.padding(top = 40.dp))
    }
    LaunchedEffect(key1 = Unit){
        delay(2000)
        navController.navigate(Screens.OnBoarding)
    }

//    Row {
//        var count by remember { mutableStateOf(0) }
//        Button(onClick = { count++ }) {
//            Text("Add")
//        }
//        Button(onClick = { count-- }) {
//            Text("Delete")
//        }
//        AnimatedContent(
//            targetState = count,
//            transitionSpec = {
//                // Compare the incoming number with the previous number.
//                if (targetState > initialState) {
//                    // If the target number is larger, it slides up and fades in
//                    // while the initial (smaller) number slides up and fades out.
//                    slideInVertically { height -> -height } + fadeIn() with
//                            slideOutVertically { height -> -height } + fadeOut()
//                } else {
//                    // If the target number is smaller, it slides down and fades in
//                    // while the initial number slides down and fades out.
//                    slideInVertically { height -> height } + fadeIn() with
//                            slideOutVertically { height -> height } + fadeOut()
//                }.using(
//                    // Disable clipping since the faded slide-in/out should
//                    // be displayed out of bounds.
//                    SizeTransform(clip = false)
//                )
//            }
//        ) { targetCount ->
//            Text(text = "$targetCount")
//        }
//    }

}

@Preview
@Composable
fun Preview(){
    SlplashScreen(navController = rememberNavController())
}