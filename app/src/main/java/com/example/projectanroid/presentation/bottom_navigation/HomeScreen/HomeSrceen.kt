package com.example.projectanroid.presentation.bottom_navigation.HomeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.projectanroid.R

import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeSrceen() {
    val listImg = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    val state = rememberPagerState(pageCount = { listImg.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 40.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
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
        HorizontalParer(state = state, listImg =listImg )
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            , modifier = Modifier
                .padding(start = 33.dp, end = 15.dp, top = 30.dp)
                .height(25.dp)
                .fillMaxWidth())
        {
            Text(text = "Popular", fontFamily = FontFamily(Font(R.font.font_yeon))
                , fontSize = 16.sp
                , textAlign = TextAlign.Center)
            Box( modifier = Modifier
                .height(25.dp)
                .width(80.dp)
                .shadow(
                    elevation = 1.dp,
                    spotColor = Color(0xFF000000.toInt()),
                    shape = RoundedCornerShape(4.dp)
                )
                .border(1.dp, Color(0xFFF4F4F4.toInt()), RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))){
                Text(text = "View Menu", fontSize = 12.sp, modifier = Modifier.align(Alignment.Center)
                    , color = Color(0xFF53E88B.toInt())
                )
            }
        }
        lazalyListFood()


    }


//        LaunchedEffect(Unit) {
//            while (true) {
//                GlobalScope.launch {
//                    val nextIndex = 1
//                }
//                delay(3000) // thời gian chờ giữa các lần cuộn (3000ms = 3 giây)
//            }
//        }

}

@Preview
@Composable
fun lazalyListFood(){
    LazyColumn(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
        item {  }
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalParer(state: PagerState, listImg: List<Int>){
    HorizontalPager(verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(start = 35.dp, end =35.dp)
        , modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        , state = state) { page ->
        Card(
        ) {
            Box(modifier = Modifier.fillMaxSize()){
                Image( modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .align(Alignment.Center),
                    painter = painterResource(id = listImg[page])
                    , contentDescription = null )
            }

        }
    }
}
@Preview
@Composable
fun preview() {
    Surface {
        HomeSrceen()

    }
}