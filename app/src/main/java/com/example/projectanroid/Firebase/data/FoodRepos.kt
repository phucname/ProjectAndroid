package com.example.projectanroid.Firebase.data

import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.module.Food
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface FoodRepos {
     suspend fun getListFood(): Flow<Resource<List<Food>>>
     suspend fun  setFood(food: Food):Boolean
}