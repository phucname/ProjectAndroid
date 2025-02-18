package com.example.projectanroid.data.Repository

import android.net.Uri
import com.example.projectanroid.data.Firebase.Resource
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreFirebasellmt @Inject constructor(val dataStorageReference: StorageReference) :
    StorgeFirebase {
    override suspend fun getImageUrl(nameImg: String): Flow<Resource<Uri>> = flow {
        emit(Resource.Loading())
        val uriImg = dataStorageReference.child("Food/" + nameImg).downloadUrl.await()
        emit(Resource.Success(uriImg))
    }

    override suspend fun setImage(byte: ByteArray, name: String): Flow<Resource<Boolean>> =
        callbackFlow {
            trySend(Resource.Loading())
            try {

                val upload = dataStorageReference.child("Food/" + name).putBytes(byte)
                upload.addOnSuccessListener {

                    trySend(Resource.Success(true))
                }.addOnFailureListener {
                    trySend(Resource.Error("faill push image"))


                }
                awaitClose {
                    upload.cancel()
                }


            } catch (ex: Error) {
                trySend(Resource.Error(ex.toString()))
            }

        }
}