package com.example.projectanroid.ui.main.bottom_navigation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectanroid.R
import com.example.projectanroid.data.module.CartFood

@Composable
fun CartSrceen (name: String){

    ItemCart()
}

@Composable
fun ItemCart(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(103.dp)
        .clip(RoundedCornerShape(22.dp))
        .background(Color(0xFFE3E3E3.toInt()))
        .padding(start = 10.dp, top = 20.dp, bottom = 10.dp, end = 10.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.banner1 )
            , contentDescription =""
            , contentScale = ContentScale.Crop
            , modifier = Modifier
                .size(62.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column (modifier =  Modifier.padding(start = 15.dp, end = 15.dp),) {
                Text(text = "Spacy fresh crab", fontSize = 15.sp,modifier = Modifier.padding(top = 10.dp))
                Text(text = "$35", fontSize = 19.sp, modifier = Modifier.padding(top = 10.dp))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally
                , verticalArrangement = Arrangement.SpaceBetween
                , modifier = Modifier.fillMaxHeight()) {
                ItemUpDowQuantily()
                Image(painter = painterResource(id =R.drawable.trash )
                    , contentDescription =""
                    , modifier = Modifier.size(26.dp))
            }
        }




    }
}

@Composable
fun ItemUpDowQuantily(){
    Row (modifier = Modifier.width(92.dp)
        , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Image(painter = painterResource(id =R.drawable.icon_minus )
            , contentDescription =""
            , modifier = Modifier.size(26.dp))
        Text(text = "1")
        Image(painter = painterResource(id =R.drawable.icon_plus )
            , contentDescription =""
            , modifier = Modifier.size(26.dp))

    }
}

@Composable
@Preview
fun GettingItemUpDowQuantily(){
    Surface {
        ItemUpDowQuantily()
    }

}
@Composable
@Preview
fun Getting(){
    Surface {
        ItemCart()
    }

}