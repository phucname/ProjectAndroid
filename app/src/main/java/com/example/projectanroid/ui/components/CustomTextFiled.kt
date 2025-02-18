package com.example.projectanroid.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object CustomTextFiled {
    @Composable
    fun TextFiledBasic(modifier: Modifier,placeable: String, onChang: (String)-> Unit
                        , value: String){
        TextField(value = value, onValueChange =onChang
            , colors = TextFieldStyleManager(unfocusedContainerColor = Color(0xFFFFFF.toInt())).textFieldColors()
            , modifier = Modifier

                .border(1.dp, Color(0xFF131327.toInt()), RoundedCornerShape(15))
                .fillMaxWidth()
            , placeholder = { Text(text = placeable) }
        )

    }
    @Composable
    fun TextFiledBasicNotBorder(modifier: Modifier,placeable: String, onChang: (String)-> Unit
                       , value: String){
        TextField(value = value, onValueChange =onChang
            , colors = TextFieldStyleManager(unfocusedContainerColor = Color(0xFFFFFF.toInt())
            , focusedContainerColor = Color.Transparent
            ).textFieldColors()
            , modifier = Modifier
                .fillMaxWidth()
            , placeholder = { Text(text = placeable) }
        )

    }

}
@Preview
@Composable
fun Preview(){
    Surface {
        CustomTextFiled.TextFiledBasicNotBorder(
            modifier = Modifier,
            placeable = "mkkkkk",
            onChang = { it },
            value = ""
        )

    }
}