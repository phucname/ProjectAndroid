package com.example.projectanroid.Firebase

import com.example.projectanroid.Firebase.data.Repository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserCaseLogin @Inject constructor(val repository: Repository) {
    operator fun invoke(email: String, passWord: String): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading<AuthResult>())
            val coin = repository.login(email, passWord )
            emit(Resource.Success<AuthResult>(coin))
        } catch(e: HttpException) {
            emit(Resource.Error<AuthResult>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<AuthResult>("Couldn't reach server. Check your internet connection."))
        }
        catch (ex : Exception){
            emit(Resource.Error<AuthResult>("Couldn't reach server. Check your internet connection."))

        }
    }

}