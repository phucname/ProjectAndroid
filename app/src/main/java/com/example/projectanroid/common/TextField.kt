package com.example.projectanroid.common

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object TextFieldStyles {
    val defaultColors
        @Composable
        get() = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = Color.White,
            disabledIndicatorColor = Color.Transparent
        )
}
data class TextFieldStyleManager(
    val focusedTextColor: Color = Color.Gray,
    val focusedIndicatorColor: Color = Color.Transparent,
    val unfocusedIndicatorColor: Color = Color.Transparent,
    val unfocusedContainerColor: Color = Color.White,
    val focusedContainerColor: Color = Color.White,
    val disabledContainerColor: Color = Color.White,
    val cursorColor: Color = Color.White,
    val disabledIndicatorColor: Color = Color.Transparent
) {
    @Composable
    fun textFieldColors() = TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        unfocusedContainerColor = unfocusedContainerColor,
        focusedContainerColor = focusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        cursorColor = cursorColor,
        disabledIndicatorColor = disabledIndicatorColor
    )
}