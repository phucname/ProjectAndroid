package com.example.projectanroid.data.module

 open  class Food (
     var id_food: String = "",
     var name_food: String ="",
     var price: Int = 0,
     var description: String = "",
     var img_food: String = "",
     val ingredients: List<String> = emptyList()

)

data class CartFood(var quantity:Int): Food(){

}

