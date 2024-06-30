package com.example.projectanroid.module

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.Firebase.conmon.DataState
import com.example.projectanroid.Firebase.data.StorgeFirebase
import com.example.projectanroid.presentation.login.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class getImageFirebase @Inject constructor(val storgeFirebase: StorgeFirebase) :ViewModel(){
    val UriImg = mutableStateOf(DataState<Uri>())
    val _UriImg = UriImg
    fun getUrlImgFirebase(nameImg: String){
        GlobalScope.launch {
            storgeFirebase.getImageUrl(nameImg).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        UriImg.value = DataState(data = result.data)
                    }

                    is Resource.Error -> {
                        UriImg.value = DataState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        UriImg.value = DataState(isLoading = true)
                    }
                }
            }

        }

    }
}