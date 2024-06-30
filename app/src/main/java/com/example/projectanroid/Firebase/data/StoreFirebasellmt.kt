package com.example.projectanroid.Firebase.data

import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import com.example.projectanroid.Firebase.Resource
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreFirebasellmt @Inject constructor(val dataStorageReference: StorageReference)
    :StorgeFirebase {
    override suspend fun getImageUrl(nameImg: String):Flow<Resource<Uri>> = flow  {
        emit(Resource.Loading())
        val uriImg  = dataStorageReference.child("Food/" + nameImg).downloadUrl.await()
        emit(Resource.Success(uriImg))





    }
}