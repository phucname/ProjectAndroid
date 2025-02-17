package com.example.projectanroid.presentation.bottom_navigation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectanroid.R
import com.example.projectanroid.common.CustomTextFiled
import com.example.projectanroid.presentation.bottom_navigation.HomeScreen.lazalyListFood
import com.example.projectanroid.presentation.bottom_navigation.HomeScreen.viewmoduleFoodList

@Composable
fun SearchSrceen ( viewmoduleFoodList: viewmoduleFoodList = hiltViewModel()) {
    val foodList by viewmoduleFoodList._dataListFood.collectAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewmoduleFoodList.listFood()
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .fillMaxSize()
        .padding(end = 20.dp, start = 20.dp)) {
        SearchBar()
        Text(text = "Menu"
            , fontSize = 24.sp
            , modifier = Modifier.padding(30.dp).height(30.dp)
        )
        lazalyListFood(foodList)
    }
}

@Composable
fun SearchBar(){
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))

            .background(Color(0xFF59C477.toInt()).copy(alpha = 0.4f))

            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 20.dp)) {
        Image(painter = painterResource(id = R.drawable.search), contentDescription ="search" )
        CustomTextFiled.TextFiledBasicNotBorder(modifier = Modifier.fillMaxHeight(), placeable = "What do you want to order?", onChang = {}, value = "")
    }
}


@Composable
@Preview
fun Getting(){
    Surface {
        SearchSrceen()
    }
}