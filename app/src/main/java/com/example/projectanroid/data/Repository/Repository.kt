package com.example.projectanroid.data.Repository

import com.example.projectanroid.data.module.Food
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface Repository {
   suspend  fun login(email: String, passWord: String): AuthResult

}