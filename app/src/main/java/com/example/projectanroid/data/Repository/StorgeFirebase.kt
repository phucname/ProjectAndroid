package com.example.projectanroid.data.Repository

import android.net.Uri
import com.example.projectanroid.data.Firebase.Resource
import kotlinx.coroutines.flow.Flow

interface StorgeFirebase {
  suspend  fun getImageUrl(nameImg: String):Flow<Resource<Uri>>
  suspend fun setImage(byte: ByteArray, nameImg : String): Flow<Resource<Boolean>>
}