package com.example.projectanroid.Firebase.data

import android.net.Uri
import com.example.projectanroid.Firebase.Resource
import kotlinx.coroutines.flow.Flow

interface StorgeFirebase {
  suspend  fun getImageUrl(nameImg: String):Flow<Resource<Uri>>

}