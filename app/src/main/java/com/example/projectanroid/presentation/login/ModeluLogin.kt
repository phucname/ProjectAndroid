package com.example.projectanroid.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.Firebase.UserCaseLogin
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Optional.empty
import javax.inject.Inject

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: AuthResult? = null,
    val error: String = ""
)
@HiltViewModel
class ModeluLogin @Inject constructor(val userCaseLogin: UserCaseLogin): ViewModel() {
    val authResult = mutableStateOf(CoinListState())
    var _authResult = authResult
    fun login(email: String, passWord: String){
        userCaseLogin.invoke(email,passWord).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    authResult.value = CoinListState(coins = result.data)
                }

                is Resource.Error -> {
                    authResult.value = CoinListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    authResult.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}