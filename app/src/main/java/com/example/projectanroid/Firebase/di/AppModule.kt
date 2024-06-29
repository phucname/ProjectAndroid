package com.example.projectanroid.Firebase.di

import com.example.projectanroid.Firebase.data.FoodRepos
import com.example.projectanroid.Firebase.data.FoodReposllmt
import com.example.projectanroid.Firebase.data.Repository
import com.example.projectanroid.Firebase.data.RepositoryIlmt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun FirebasAuthProvide ():FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun RepositoryllmtProvide(firebaseAuth: FirebaseAuth):Repository{
        return RepositoryIlmt(firebaseAuth)

    }
    @Provides
    @Singleton
    fun FirebasDataProvide ():DatabaseReference{
       return Firebase.database.getReference("Foododer")
    }

    @Provides
    @Singleton
    fun RepositoryFirebaseDatallmtProvide(databaseReference: DatabaseReference):FoodRepos{
        return FoodReposllmt(databaseReference)

    }

}