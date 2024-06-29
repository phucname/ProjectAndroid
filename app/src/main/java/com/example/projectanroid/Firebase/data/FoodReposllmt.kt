package com.example.projectanroid.Firebase.data

import com.example.projectanroid.Firebase.Resource
import com.example.projectanroid.module.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoodReposllmt @Inject constructor (val databaseReferencee: DatabaseReference): FoodRepos {
    override suspend fun getListFood(): Flow<Resource<List<Food>>> = callbackFlow {
        val databaseReference = databaseReferencee.child("Food")

        // Phát tín hiệu Loading
        trySend(Resource.Loading())

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val foods = dataSnapshot.children.mapNotNull { it.getValue(Food::class.java) }
                trySend(Resource.Success(foods))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(
                    Resource.Error<List<Food>>(
                        databaseError.toException().toString()
                    )
                ).isFailure
            }
        }

        databaseReference.addValueEventListener(listener)

        // Đảm bảo rằng listener được gỡ bỏ khi không còn cần thiết
        awaitClose {
            databaseReference.removeEventListener(listener)
        }


    }
}


