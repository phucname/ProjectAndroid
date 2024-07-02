package com.example.projectanroid.presentationMain.Food

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.Firebase.UserCaseSetFood
import com.example.projectanroid.Firebase.conmon.DataState
import com.example.projectanroid.module.Food
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ModuleAddFood @Inject constructor(val userCaseSetFood: UserCaseSetFood, val databaseReference: DatabaseReference):ViewModel() {
    var stateAddFood = mutableStateOf(DataState<Boolean>())
    val _stateAddFood = stateAddFood

    fun  addFood(food: Food){
        var newFood = food
        val newDataChid = databaseReference.push()
        newFood.id_food = newDataChid.key!!
        userCaseSetFood.invoke(newFood).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    stateAddFood.value = DataState(data = true)

                }

                is Resource.Error -> {
                    stateAddFood.value = DataState(error = result.message!!)
                }

                is Resource.Loading -> {
                    stateAddFood.value = DataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }




}