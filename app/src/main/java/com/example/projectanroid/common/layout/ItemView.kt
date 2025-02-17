package com.example.projectanroid.common.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectanroid.R
import com.example.projectanroid.module.Food

object ItemView {

    @Composable
    fun ItemFoodOder(itemFood: Food) {

        Row(
            modifier = Modifier
                .height(87.dp)
                .clip(RoundedCornerShape(22.dp))
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(0.8f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.itme_food_oder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Text(
                    text = itemFood.name_food,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Column {
                Text(
                    text = "$${itemFood.price}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF10C554),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .size(53.dp, 14.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(2.dp))

                        .background(Color(0xFF53E88B.toInt()))
                ) {
                    Text(
                        lineHeight = 7.sp,
                        text = "Add to cart",
                        fontSize = 6.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center).height(6.dp)
                    )
                }
            }


        }

    }
}

@Preview
@Composable
fun Preview() {

    ItemView.ItemFoodOder(
        itemFood = Food(
            "",
            "KFC ",
            55,
            "ngonnnn",
            "",
            listOf("","")
        )
    )


}


