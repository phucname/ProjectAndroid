package com.example.projectanroid.Firebase

import com.example.projectanroid.Firebase.data.FoodReposllmt
import com.example.projectanroid.module.Food
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserCaseSetFood @Inject constructor(val reposllmt: FoodReposllmt) {
    operator fun invoke (food: Food): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val issucces = reposllmt.setFood(food)
            emit(Resource.Success(issucces))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
        catch (ex : Exception){
            emit(Resource.Error(ex.toString()))
        }

    }
}