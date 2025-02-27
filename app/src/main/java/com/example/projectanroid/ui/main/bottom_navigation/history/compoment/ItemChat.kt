package com.example.projectanroid.ui.main.bottom_navigation.history.compoment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectanroid.R
import com.example.projectanroid.ui.main.bottom_navigation.history.DataChat
import com.example.projectanroid.ui.main.bottom_navigation.history.dataTemple

@Composable
fun ItemTextChat(dataChat: DataChat) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(200.dp)
            .background(Color.DarkGray.copy(alpha = 0.7f))  // Cắt bo góc trước
            .padding( 10.dp, 5.dp)
    ) {
        Text(text = dataChat.content, color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = dataChat.time, color = Color.White)
    }

}

@Composable
fun ItemChatMain(dataChat: DataChat){
    Row(horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth())
    {
        ItemTextChat(dataChat)

            Image(painter = painterResource(id = R.drawable.g10)
                , contentDescription = null
                , modifier = Modifier.size(50.dp))
    }
}
@Composable
fun ItemChatGuest(dataChat: DataChat){
    Row(horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth())
    {
        Image(painter = painterResource(id = R.drawable.g10)
            , contentDescription = null
            , modifier = Modifier.size(50.dp)
        )
        ItemTextChat(dataChat)

    }
}
@Composable
@Preview(showBackground = false)
fun GetItemChat(){
        ItemTextChat(dataTemple[0])

}
@Composable
@Preview(showBackground = false)
fun GetChatMain(){
    ItemChatMain(dataTemple[0])

}
@Composable
@Preview(showBackground = false)
fun GetChatGuest(){
    ItemChatGuest(dataTemple[0])

}