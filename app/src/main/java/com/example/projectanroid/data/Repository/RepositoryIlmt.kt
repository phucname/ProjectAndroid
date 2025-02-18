package com.example.projectanroid.data.Repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryIlmt @Inject constructor(val firebaseAuth: FirebaseAuth): Repository {
    override suspend fun login(email: String, passWord: String): AuthResult {
               return  firebaseAuth.signInWithEmailAndPassword(email, passWord).await()

        }


}