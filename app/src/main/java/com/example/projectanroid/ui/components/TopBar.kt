package com.example.projectanroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectanroid.R

@Composable
fun TopBar(modifier: Modifier){
    Row(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .height(70.dp)
            .fillMaxWidth()
        , horizontalArrangement = Arrangement.SpaceBetween
        , verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Explore Your Favorite Food",
            fontSize = 24.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily(Font(R.font.font_yeon))
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_notifications_none_24),
            contentDescription = null
        )
    }
}