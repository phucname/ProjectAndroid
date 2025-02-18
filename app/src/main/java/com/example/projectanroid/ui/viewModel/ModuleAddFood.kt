package com.example.projectanroid.ui.viewModel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectanroid.data.Firebase.Resource
import com.example.projectanroid.data.Firebase.UserCaseSetFood
import com.example.projectanroid.contanst.DataState
import com.example.projectanroid.data.module.Food
import com.example.projectanroid.data.Firebase.GetImageFirebaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ModuleAddFood @Inject constructor(val userCaseSetFood: UserCaseSetFood):ViewModel()
{
    lateinit var getImageFirebase: GetImageFirebaseViewModel

    fun setViewModel(viewModel: GetImageFirebaseViewModel) {
        this.getImageFirebase = viewModel
    }
    private val _stateAddFood = mutableStateOf(DataState<Boolean>())
    val stateAddFood: State<DataState<Boolean>> get() = _stateAddFood

    fun  addFood(food: Food){
        GlobalScope.launch {
                userCaseSetFood.invoke(food).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _stateAddFood.value = DataState(data = result.data)
                        }
                        is Resource.Error -> {
                            _stateAddFood.value = DataState(error = result.message!!)
                        }
                        is Resource.Loading -> {
                            _stateAddFood.value = DataState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }