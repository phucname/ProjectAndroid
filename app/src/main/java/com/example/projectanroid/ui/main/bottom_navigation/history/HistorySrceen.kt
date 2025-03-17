package com.example.projectanroid.ui.main.bottom_navigation.history

import android.content.Context
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectanroid.ui.components.TextFieldStyleManager
import com.example.projectanroid.ui.main.bottom_navigation.history.compoment.ItemChat
import kotlinx.coroutines.launch

//@Composable
//fun rememberKeyboardHeightWithLocalView(): Int {
//    val view = LocalView.current
//    val keyboardHeight = remember { mutableStateOf(0) }
//    DisposableEffect(view) {
//        val listener = ViewTreeObserver.OnGlobalLayoutListener {
//            val rect = Rect()
//            view.getWindowVisibleDisplayFrame(rect)
//            val screenHeight = view.height
//            val heightDiff = screenHeight - rect.bottom
//            keyboardHeight.value = if (heightDiff > screenHeight * 0.15) heightDiff else 0
//        }
//        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
//
//        onDispose {
//            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
//        }
//    }
//
//    return keyboardHeight.value
//}

@Composable
fun HistorySrceen(appLication: Context) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val lazyconlum = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val listChat = remember { mutableStateListOf<DataChat>() }
    LaunchedEffect(Unit) {
        listChat.addAll(dataTemple)
        println(listChat.size)
    }
    var textChat by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.height(if (!isFocused) screenHeight.dp else ((screenHeight)/2).dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .clickable(onClick = {
                    focusManager.clearFocus()
                    isFocused = false
                }), state = lazyconlum
        ) {
            items(listChat.size) { index ->
                val hideTime = index == listChat.size - 1 ||
                        (index < listChat.size - 1 && listChat[index].isMainUser != listChat[index + 1].isMainUser)
                val hideImg =
                    !(index > 0 && listChat[index].isMainUser == listChat[index - 1].isMainUser)
                ItemChat(
                    dataChat = listChat[index].copy(
                        HidenImg = hideImg,
                        HidenTime = hideTime
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            coroutineScope.launch {
                lazyconlum.scrollToItem(listChat.size - 1)
            }
        }
        TextField(value = textChat,
            onValueChange = {textChat = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    coroutineScope.launch {
                        println("netx")
                        listChat.add(DataChat(content = textChat, isMainUser = true, time = "23:00"))
                        textChat = ""
                        focusManager.clearFocus()
                        isFocused = false                    }
                   }
                , onGo = { println("go") }
                , onPrevious = { println("Previous") }
                , onDone = { println("done") }
                , onSearch = { println("search") }
                , onSend = { println("send") }
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {

                        isFocused = true
                    }
                },
            colors = TextFieldStyleManager(unfocusedContainerColor = Color(0xFFFFFF.toInt())).textFieldColors(),
            placeholder = { Text(text = "TextField") })
    }
}

@Composable
@Preview
fun setviwe() {
}