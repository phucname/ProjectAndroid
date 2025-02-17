package com.example.projectanroid.module

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.Firebase.conmon.DataState
import com.example.projectanroid.Firebase.data.StorgeFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class GetImageFirebaseViewModel @Inject constructor(
    private val storageFirebase: StorgeFirebase
) : ViewModel() {

    val uriImg = mutableStateOf(DataState<Uri>())
    val stateSetImage = mutableStateOf(DataState<Boolean>())

    fun getUrlImgFirebase(nameImg: String) {
        viewModelScope.launch {
            storageFirebase.getImageUrl(nameImg).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        uriImg.value = DataState(data = result.data)
                    }
                    is Resource.Error -> {
                        uriImg.value = DataState(error = result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        uriImg.value = DataState(isLoading = true)
                    }
                }
            }
        }
    }

    fun setImageFirebase(uriImag: Uri, nameImg: String, context: Context) {
        viewModelScope.launch {
            val byteArray = getBytesFromUri(context, uriImag)
            if (byteArray != null) {
                storageFirebase.setImage(byteArray, nameImg).collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            stateSetImage.value = DataState(data = true)
                        }
                        is Resource.Error -> {
                            stateSetImage.value = DataState(error = resource.message ?: "Not Set Image")
                        }
                        is Resource.Loading -> {
                            stateSetImage.value = DataState(isLoading = true)
                        }
                    }
                }
            } else {
                stateSetImage.value = DataState(error = "Cannot get byte data")
            }
        }
    }

    private fun getBytesFromUri(context: Context, uri: Uri): ByteArray? {
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
