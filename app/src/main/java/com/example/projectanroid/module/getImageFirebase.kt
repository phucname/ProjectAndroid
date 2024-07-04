package com.example.projectanroid.module

import android.content.Context
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
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class getImageFirebase @Inject constructor(val storgeFirebase: StorgeFirebase) :ViewModel(){
    val UriImg = mutableStateOf(DataState<Uri>())
    val _UriImg = UriImg
    val StateSetImage = mutableStateOf(DataState<Boolean>())
    val _stateSetImage = StateSetImage
    fun getUrlImgFirebase(nameImg: String){
        GlobalScope.launch {
            storgeFirebase.getImageUrl(nameImg).onEach { result ->
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

    fun setImageFirebase (uriImag: Uri, nameImg: String, context: Context){
        viewModelScope.launch{
            val byteArray = getBytesFromUri(context = context, uriImag)
            if (byteArray != null){
                storgeFirebase.setImage(byteArray, nameImg).collect { resource ->
                    when(resource){
                        is Resource.Success -> {
                            StateSetImage.value = DataState(data = true)}
                        is Resource.Error -> {
                            StateSetImage.value = DataState(error = resource.message ?: "Not Set Image")
                        }
                        is Resource.Loading -> StateSetImage.value = DataState(isLoading = true)
                    }
                }
            }else StateSetImage.value = DataState(error = "not get byte d")

        }
    }

    fun getBytesFromUri(context: Context, uri: Uri): ByteArray? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val byteBuffer = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var len: Int
            while (inputStream?.read(buffer).also { len = it ?: -1 } != -1) {
                byteBuffer.write(buffer, 0, len)
            }
            byteBuffer.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}