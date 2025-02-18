package com.example.projectanroid.data.Repository

import com.example.projectanroid.data.Firebase.Resource
import com.example.projectanroid.data.module.Food
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow


interface FoodRepos {
     suspend fun getListFood(): Flow<Resource<List<Food>>>
     suspend fun  setFood(food: Food): Task<Void>
}