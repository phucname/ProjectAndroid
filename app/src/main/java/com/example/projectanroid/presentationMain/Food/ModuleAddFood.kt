package com.example.projectanroid.presentationMain.Food


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.Firebase.UserCaseSetFood
import com.example.projectanroid.Firebase.conmon.DataState
import com.example.projectanroid.Firebase.data.FoodRepos
import com.example.projectanroid.Firebase.data.FoodReposllmt
import com.example.projectanroid.Firebase.data.StorgeFirebase
import com.example.projectanroid.module.Food
import com.example.projectanroid.module.getImageFirebase
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ModuleAddFood @Inject constructor(val userCaseSetFood: UserCaseSetFood,val getImageFirebase: getImageFirebase
):ViewModel() {
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