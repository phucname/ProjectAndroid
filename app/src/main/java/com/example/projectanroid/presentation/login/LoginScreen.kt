package com.example.projectanroid.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectanroid.R
import com.example.projectanroid.common.TextFieldStyleManager

@Composable
fun LoginScreen() {
    val colorStops = listOf(
        Color(0xFF53E88B.toInt()),
        Color(0xFF15BE77.toInt())
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(end = 25.dp, start = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.g10)
            , contentDescription = null
            , modifier = Modifier
                .padding(top = 59.dp)
                .size(92.dp, 91.dp))
        Text(text = "Waves Of Food", fontSize = 40.sp, fontWeight = FontWeight.W400
            , fontFamily = FontFamily(Font(R.font.font_yeon)) )
        Text(text = "Deliver Favorite Food", fontSize = 14.sp, fontWeight = FontWeight.W700
            , fontFamily = FontFamily(Font(R.font.font_yeon))
            , color = Color(0xFF53E88B.toInt())
            , modifier = Modifier.padding(top = 20.dp))
        Text(text = "Login To Your Account", fontSize = 20.sp, fontWeight = FontWeight.W700
            , fontFamily = FontFamily(Font(R.font.font_yeon))
            , color = Color(0xFF53E88B.toInt())
            , modifier = Modifier.padding(top = 20.dp))
        Column(modifier = Modifier.testTag("FormLogin")){
            TextField(value = "", onValueChange ={}
                , colors = TextFieldStyleManager(unfocusedContainerColor = Color(0xFFFFFF.toInt())).textFieldColors()
                , modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .border(1.dp, Color(0xFF131327.toInt()), RoundedCornerShape(15))
                    .fillMaxWidth()
                , placeholder = { Text(text = "Email or phone number")}
            )
            TextField(value = "", onValueChange ={}
                , colors = TextFieldStyleManager(unfocusedContainerColor = Color(0xFFFFFFFF.toInt())).textFieldColors()
                , modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .border(1.dp, Color(0xFF131327.toInt()), RoundedCornerShape(15))
                    .fillMaxWidth()
                , placeholder = { Text(text = "PassWord", color = Color(0xFF3B3B3B.toInt()), fontWeight = FontWeight.W400)}
            )
        }
        Column(modifier = Modifier
            .padding(top = 20.dp, bottom = 30.dp)
            .size(124.dp, 41.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "or", fontSize = 10.sp )
            Text(text = "Continue With", fontSize = 20.sp)
        }
        Row(modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CustomLoginWith(R.drawable.facebook_3_1, "FaceBook")
            CustomLoginWith(R.drawable.google_icon_2, "Google")

        }
        Box(modifier = Modifier
            .padding(bottom = 90.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .size(157.dp, 57.dp)
            .background(Brush.linearGradient(colors = colorStops))

        ){
            Text(text = "Login", fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.font_yeon)),
                modifier = Modifier.align(Alignment.Center)
                , color = Color.White
            )
        }
    }
}

@Composable
fun CustomLoginWith(image: Int, nameLogin:String){
    Row(modifier = Modifier
        .size(152.dp, 57.dp)
        .border(1.dp, Color(0xFF686767), shape = RoundedCornerShape(15))
        .clip(RoundedCornerShape(15))
        .padding(end = 20.dp, start = 20.dp)
        , horizontalArrangement = Arrangement.SpaceBetween
        , verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(painter = painterResource(id = image), contentDescription = null, modifier = Modifier.size(25.dp) )
        Text(text = nameLogin, fontSize = 14.sp)
    }
}
@Preview
@Composable
fun Preview(){
    LoginScreen()
}