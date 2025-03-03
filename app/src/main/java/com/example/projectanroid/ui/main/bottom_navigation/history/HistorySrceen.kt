package com.example.projectanroid.ui.main.bottom_navigation.history

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.projectanroid.MainAppLication
import com.example.projectanroid.ui.components.TextFieldStyleManager
import com.example.projectanroid.ui.main.bottom_navigation.history.compoment.ItemChat
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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
    Box(
        modifier = Modifier.height(if (!isFocused) screenHeight.dp else (screenHeight / 2).dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
                .clickable(onClick = {
                    focusManager.clearFocus()
                    isFocused = false
                }), state = lazyconlum
        ) {
            items(dataTemple.size) { index ->
                val hideTime = index == dataTemple.size - 1 ||
                        (index < dataTemple.size - 1 && dataTemple[index].isMainUser != dataTemple[index + 1].isMainUser)
                val hideImg =
                    !(index > 0 && dataTemple[index].isMainUser == dataTemple[index - 1].isMainUser)
                ItemChat(
                    dataChat = dataTemple[index].copy(
                        HidenImg = hideImg,
                        HidenTime = hideTime
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            coroutineScope.launch {
                lazyconlum.scrollToItem(dataTemple.size - 1)
            }
        }
        TextField(value = "",
            onValueChange = { },
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