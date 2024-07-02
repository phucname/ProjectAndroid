package com.example.projectanroid.module

data class Food (
    var id_food: String = "",
    val name_food: String ="",
    val price: Int = 0,
    val description: String = "",
    val img_food: String = "",
    val Ingredients: List<String>
)