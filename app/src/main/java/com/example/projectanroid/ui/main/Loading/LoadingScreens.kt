package com.example.projectanroid.ui.main.Loading

import android.content.Context
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.projectanroid.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun Loading(application: Context){
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.animated_vector)
    var atEnd by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = "Timer",
            modifier = Modifier.size(100.dp).clickable {
                atEnd = !atEnd
            },
            contentScale = ContentScale.Crop
        )
    }



}