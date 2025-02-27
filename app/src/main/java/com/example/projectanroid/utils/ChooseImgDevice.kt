package com.example.projectanroid.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun ChooseOneImg(onImagePicked: (Uri?) -> Unit) {
    var uri by remember { mutableStateOf<Uri?>(null) }

    // Hãy đảm bảo launcher chỉ được khởi tạo trong Composable function
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { resultUri ->
        uri = resultUri
        onImagePicked(resultUri) // Trả lại uri cho composable gọi
    }

    // Khởi chạy trình chọn hình ảnh khi Composable được gọi
    LaunchedEffect(Unit) {
        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}



