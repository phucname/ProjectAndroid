package com.example.projectanroid.presentation.set_location

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectanroid.R
import com.example.projectanroid.common.ComposableModifiter
import com.example.projectanroid.module.Permission



@Composable
fun SetLocationScreen() {
    var textLocation by remember {
        mutableStateOf("Choose Your Location ")
    }
    val context = LocalContext.current
    val moduleLocationManager = Permission(context)
    moduleLocationManager.CallPermissionLocation()

    Box(modifier = ComposableModifiter.modifierBasci.padding(start = 35.dp, end = 15.dp)) {
        Column {
            Text(
                text = "Choose Your Location",
                modifier = Modifier.padding(top = 50.dp, bottom = 30.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.font_yeon)),
            )
            Row(
                modifier = Modifier
                    .height(57.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15))
                    .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = textLocation, fontSize = 14.sp)
                Image(painter = painterResource(id = R.drawable.baseline_download_for_offline_24),
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = {
                    moduleLocationManager.getLocatoin(onchan = {textLocation = it})
                    }))

            }
        }
        Text(
            text = "To provide you with the best dining experience, " +
                    "we need your permission to access your device's location. " +
                    "By enabling location services, we can offer personalized" +
                    " restaurant recommendations, accurate delivery estimates, " +
                    "and ensure a seamless food delivery experience.\"",
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(alignment = Alignment.BottomStart),
            fontSize = 16.sp, fontWeight = FontWeight.W400,
            lineHeight = 20.sp
        )


    }
}


@Preview
@Composable
fun Preview(){
    Surface(onClick = { /*TODO*/ }) {
        SetLocationScreen()

    }
}