package com.example.projectanroid.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectanroid.R
import com.example.projectanroid.common.Screens

import com.example.projectanroid.common.TextContent

@Composable
fun OnboardingScreen(navController: NavController) {
    val colorStops = listOf(
        Color(0xFF53E88B.toInt()),
        Color(0xFF15BE77.toInt())
    )
    val colorStopsHover = listOf(
        Color(0xA82962FF),
        Color(0xFFBE4B3E.toInt())
    )
    val inter = remember {
        MutableInteractionSource()
    }
    val  hover = inter.collectIsPressedAsState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)) {
            Image(painter = painterResource(id = R.drawable.g10_2)
                , contentDescription = null
                , modifier = Modifier.padding(top = 120.dp, bottom = 59.dp))
            Text(text = TextContent.contentOnBoarding, fontSize = 20.sp, fontWeight = FontWeight.W400
                //, fontFamily = FontFamily(Font(R.font.font_yeonsung_regular))
                , color = Color(0xFF53E88B.toInt())
                , maxLines = 2
                , textAlign = TextAlign.Center
            )

        }

        Box(modifier = Modifier
            .padding(bottom = 90.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .size(157.dp, 57.dp)
            .background(
                if (hover.value) Brush.linearGradient(colors = colorStopsHover) else Brush.linearGradient(
                    colors = colorStops
                )
            )
            .align(Alignment.BottomCenter)
            .clickable(
                interactionSource = inter,
                indication = rememberRipple(),
                onClick = { navController.navigate(Screens.Login) })
            .hoverable(inter)
            .focusable(interactionSource = inter, enabled = true)

        ){
            Text(text = "Next", fontSize = 20.sp
                //, fontFamily = FontFamily(Font(R.font.font_yeonsung_regular))
                , modifier = Modifier.align(Alignment.Center)
                , color = Color.White
            )
        }

    }




}

@Preview
@Composable
fun Preview(){
    OnboardingScreen(navController = rememberNavController())
}