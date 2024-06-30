package com.example.projectanroid.presentationMain.Food

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.projectanroid.R
import com.example.projectanroid.common.CustomTextFiled
import com.example.projectanroid.common.TextFieldStyleManager
import com.example.projectanroid.module.getImageFirebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream


fun getBytesFromUri(context: Context, uri: Uri): ByteArray? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val byteBuffer = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var len: Int
        while (inputStream?.read(buffer).also { len = it ?: -1 } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        byteBuffer.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
@Composable
fun AddFood() {
    var selectedImg by remember { mutableStateOf<Uri?>(null) }
    var templeImg by remember {
        mutableStateOf<Uri?>(null)
    }
    val listIngre = remember { mutableStateListOf<String>() }

    var valueTest by remember { mutableStateOf("") }
    val firebaseStorage = FirebaseStorage.getInstance()
    val mountainImagesRef = firebaseStorage.reference.child("Food/mountains.jpg")


    if(selectedImg != null)
    {
        templeImg = selectedImg
        var getbye  = getBytesFromUri(LocalContext.current, templeImg!!)
        if (getbye != null){
            val uploadTask = mountainImagesRef.putBytes(getbye)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }
        }
    }
    val handle = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {selectedImg = it})

    Column(modifier = Modifier
        .fillMaxSize().verticalScroll(enabled = true, reverseScrolling = true, state = ScrollState(2))
        .padding(start = 10.dp, top = 10.dp, end = 10.dp)){
        Row (modifier = Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.icon_3)
                , contentDescription = null
                , modifier = Modifier.weight(0.1f))
            Text(text = "Add Item", fontFamily = FontFamily(Font(R.font.font_yeon))
                , fontSize = 40.sp
                , modifier = Modifier
                    .padding(start = 50.dp)
                    .weight(0.8f))
        }
        CustomTextFiled.TextFiledBasic(modifier = Modifier, placeable ="Item name" , onChang ={} , value ="" )
        TextField(value = "",
            onValueChange = {  },
            colors = TextFieldStyleManager(unfocusedContainerColor = Color(0xFFFFFF.toInt())).textFieldColors(),
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .border(1.dp, Color(0xFF131327.toInt()), RoundedCornerShape(15))
                .fillMaxWidth(),
            placeholder = { Text(text = "Item Price") }
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .clip(RoundedCornerShape(15))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
            .padding(start = 10.dp, end = 10.dp)
        ){
            Text(text = "Item Image"
                , fontFamily = FontFamily(Font(R.font.font_yeon))
                , fontSize = 14.sp
                , lineHeight = 17.5.sp
                , modifier = Modifier
                    .align(Alignment.CenterStart)
            )
            Image(painter = painterResource(id = R.drawable.baseline_download_for_offline_24), contentDescription = null
                , modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick =
                    {
                        handle.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }))
        }
        Image(painter = rememberImagePainter(data = templeImg), contentDescription = null
            , contentScale = ContentScale.FillBounds
            , modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .width(175.dp)
                .height(117.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(10.dp))
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .clip(RoundedCornerShape(15))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
        ){
            Text(text = "Short Description"
                , fontFamily = FontFamily(Font(R.font.font_yeon))
                , fontSize = 14.sp
                , lineHeight = 17.5.sp

                , modifier = Modifier
                    .padding(start = 20.dp)
                    .align(Alignment.CenterStart))
        }
        TextField(value = valueTest, onValueChange = {valueTest = it}
            , keyboardOptions = KeyboardOptions.run {
                Default.copy(
                        imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Text
                    )
            },
            keyboardActions = KeyboardActions(
              onNext = {
                listIngre.add(valueTest)
                  println("list:$listIngre")
              }
            )
            , modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .border(2.dp, Color.DarkGray, RoundedCornerShape(8))
                .height(81.dp)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .clip(RoundedCornerShape(15))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
        ){
            Text(text = "Ingredients"
                , fontFamily = FontFamily(Font(R.font.font_yeon))
                , fontSize = 14.sp
                , lineHeight = 17.5.sp
                , modifier = Modifier
                    .padding(start = 20.dp)
                    .align(Alignment.CenterStart))
        }
        LazyColumn(modifier = Modifier.height(200.dp)) {
            items(items = listIngre, itemContent = { item ->
                Row {
                    Image(painter = painterResource(id = R.drawable.baseline_download_for_offline_24), contentDescription = null )
                    Text(text = "$item", fontSize = 15.sp)
                }

            })
        }
    }
}



@Preview
@Composable
fun Preview(){
    Surface( modifier = Modifier.fillMaxSize()) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(53.dp)
                .clip(RoundedCornerShape(15))
                .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
            ){
                Text(text = "Short Description"
                    , fontFamily = FontFamily(Font(R.font.font_yeon))
                    , fontSize = 14.sp
                    , lineHeight = 17.5.sp
                    , modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.CenterStart))
            }
            Text(text = ""
                , modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .border(2.dp, Color.DarkGray, RoundedCornerShape(8))
                    .height(81.dp)
                , fontSize = 14.sp
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(53.dp)
                .clip(RoundedCornerShape(15))
                .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
            ){
                Text(text = "Ingredients"
                    , fontFamily = FontFamily(Font(R.font.font_yeon))
                    , fontSize = 14.sp
                    , lineHeight = 17.5.sp
                    , modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.CenterStart))
            }
        }


    }
}