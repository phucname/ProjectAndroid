package com.example.projectanroid.ui.detail

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.projectanroid.R
import com.example.projectanroid.ui.components.CustomTextFiled
import com.example.projectanroid.ui.components.TextFieldStyleManager
import com.example.projectanroid.ui.components.TextFieldStyles
import com.example.projectanroid.data.module.Food
import com.example.projectanroid.data.Firebase.GetImageFirebaseViewModel
import com.example.projectanroid.ui.viewModel.ModuleAddFood
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AddFood(addFood: ModuleAddFood = hiltViewModel()
            , moduleImageFirebase: GetImageFirebaseViewModel = hiltViewModel()) {

    val stateAddFood = addFood.stateAddFood.value
    val stateImage = moduleImageFirebase.stateSetImage.value
    val listIngres = remember { mutableStateListOf<String>() }
    var selectedImg by remember { mutableStateOf<Uri?>(null) }
    var templeImg by remember { mutableStateOf<Uri?>(Uri.EMPTY) }
    var valueTest by remember { mutableStateOf("") }
    var valueName by remember { mutableStateOf("") }
    var valuePrice by remember { mutableStateOf("0") }
    var valueShortDescription by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var addFoodTriggered by remember { mutableStateOf(false) }
    var food by remember {
        mutableStateOf(
            Food(
                "",
                valueName,
                valuePrice.toInt(),
                valueShortDescription,
                "",
                listIngres
            )
        )
    }

    if(selectedImg !=null){
        templeImg = selectedImg
    }

    if (stateImage.data == true && addFoodTriggered) {
        addFoodTriggered = false
        food.description = valueShortDescription
        food.name_food = valueName
        food.price = valuePrice.toInt()
        addFood.addFood(food)
    }

    if (stateAddFood.data == true && !addFoodTriggered) {
        println("add Food success")
    }
    val handle =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { selectedImg = it })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, reverseScrolling = true, state = ScrollState(2))
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
    ) {
        TopBarAddFood()
        CustomTextFiled.TextFiledBasic(
            modifier = Modifier,
            placeable = "Item name",
            onChang = { valueName = it },
            value = valueName
        )
        CustomTextFiled.TextFiledBasic(
            modifier = Modifier,
            placeable = "Item Price",
            onChang = { valuePrice = it },
            value = valuePrice
        )
        AddImgFood(HandleGetImgDevice = handle)
        Image(
            painter = rememberImagePainter(data = templeImg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .width(175.dp)
                .height(117.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(10.dp))
        )
        AddDescriptionFood(valeDescription = valueShortDescription
            , onChangeDescription = {value ->valueShortDescription = value})
        TextField(value = valueTest,
            onValueChange = { valueTest = it },
            keyboardOptions = KeyboardOptions.run {
                Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    listIngres.add(valueTest)
                    valueTest = ""
                    keyboardController!!.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.DarkGray, RoundedCornerShape(10)),
            colors = TextFieldStyleManager().textFieldColors(),
            placeholder = { Text(text = "Ingredients") }
        )
        LazyColumnIngredients(listIngredients = listIngres)
        Button(

            onClick = {
                GlobalScope.launch {
                    val databaseReference = FirebaseDatabase.getInstance().reference.push()
                    println("key:${databaseReference.key}")
                    food.id_food = databaseReference.key!!
                    food.img_food = food.id_food
                    if (!addFoodTriggered) {
                        addFoodTriggered = true
                        moduleImageFirebase.setImageFirebase(templeImg!!,databaseReference.key!!,context)
                    }
                }
            },
            modifier = Modifier
                .size(157.dp, 57.dp),
            shape = RoundedCornerShape(15),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53E88B.toInt()))
        ) {
            Text(text = "Add Item", fontSize = 20.sp)
        }
    }
}

@Composable
fun AddImgFood(HandleGetImgDevice: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .clip(RoundedCornerShape(15))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Text(
            text = "Item Image",
            fontFamily = FontFamily(Font(R.font.font_yeon)),
            fontSize = 14.sp,
            lineHeight = 17.5.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_download_for_offline_24),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick =
                {
                    HandleGetImgDevice.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                })
        )
    }

}

@Composable
fun AddDescriptionFood(valeDescription:String, onChangeDescription:(String)-> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .clip(RoundedCornerShape(15))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(15))
    ) {
        Text(
            text = "Short Description",
            fontFamily = FontFamily(Font(R.font.font_yeon)),
            fontSize = 14.sp,
            lineHeight = 17.5.sp,
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterStart)
        )
    }
    TextField(
        value = valeDescription,
        onValueChange = { onChangeDescription },
        colors = TextFieldStyles.defaultColors,
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8))
            .height(81.dp)
    )
}
@Composable
fun LazyColumnIngredients(listIngredients: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .heightIn(min = 80.dp, max = 300.dp)
    ) {
        items(items = listIngredients, itemContent = { item ->
            Row {
                Image(
                    painter = painterResource(id = R.drawable.baseline_download_for_offline_24),
                    contentDescription = null
                )
                Text(text = item, fontSize = 15.sp)
            }

        })
    }
}

@Composable
fun TopBarAddFood() {
    Surface {
        Row(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_3),
                contentDescription = null,
                modifier = Modifier.weight(0.1f)
            )
            Text(
                text = "Add Item",
                fontFamily = FontFamily(Font(R.font.font_yeon)),
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .weight(0.8f)
            )
        }
    }

}


