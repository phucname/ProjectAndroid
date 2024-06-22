package com.example.projectanroid.Firebase.data

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface Repository {
   suspend  fun login(email: String, passWord: String): AuthResult
}