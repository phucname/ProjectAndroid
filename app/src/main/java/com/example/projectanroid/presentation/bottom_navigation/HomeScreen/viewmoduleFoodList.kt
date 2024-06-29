package com.example.projectanroid.presentation.bottom_navigation.HomeScreen

import androidx.lifecycle.ViewModel
import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.Firebase.data.FoodRepos
import com.example.projectanroid.module.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewmoduleFoodList @Inject constructor(val foodRepos: FoodRepos) :ViewModel(){
    val datalistFood = Channel<List<Food>>()
    val _dataListFood = datalistFood.receiveAsFlow()
    fun listFood (){
        GlobalScope.launch{
            foodRepos.getListFood().collect{request ->
                when(request){
                   is Resource.Loading ->{
                        datalistFood.send(emptyList())
                   }
                    is Resource.Success ->{
                        request.data?.let { datalistFood.send(it) }
                    }

                    is Resource.Error -> TODO()
                }
            }
        }

    }
}