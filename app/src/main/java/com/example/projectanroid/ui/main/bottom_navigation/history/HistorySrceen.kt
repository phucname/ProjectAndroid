package com.example.projectanroid.ui.main.bottom_navigation.history

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectanroid.ui.main.bottom_navigation.history.compoment.ItemChatGuest
import com.example.projectanroid.ui.main.bottom_navigation.history.compoment.ItemChatMain

@Composable
fun HistorySrceen (name: String){
    LazyColumn() {
        items(items = dataTemple, itemContent =  {item ->
            if (item.main) ItemChatMain(dataChat = item) else ItemChatGuest(dataChat = item)
            Spacer(modifier = Modifier.height(20.dp))
        })


    }
}


@Composable
@Preview
fun setviwe(){
    HistorySrceen(name = "D")
}