package com.example.projectanroid.Firebase.data

import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryIlmt @Inject constructor(val firebaseAuth: FirebaseAuth): Repository {
    override suspend fun login(email: String, passWord: String): AuthResult {
               return  firebaseAuth.signInWithEmailAndPassword(email, passWord).await()

        }

}