package com.example.projectanroid.data.Firebase

import com.example.projectanroid.data.Repository.FoodReposllmt
import com.example.projectanroid.data.module.Food
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserCaseSetFood @Inject constructor(val reposllmt: FoodReposllmt) {
    operator fun invoke (food: Food): Flow<Resource<Boolean>> = callbackFlow {
        val  task = reposllmt.setFood(food)
        try {
            trySend(Resource.Loading())

            task.addOnSuccessListener {
                trySend(Resource.Success(true))
            }.addOnFailureListener {
                trySend(Resource.Error(it.toString()))
            }

        } catch(e: HttpException) {
            trySend(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            trySend(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
        catch (ex : Exception){
            trySend(Resource.Error(ex.toString()))
        }


        awaitClose{
           task.isCanceled
        }
    }
}