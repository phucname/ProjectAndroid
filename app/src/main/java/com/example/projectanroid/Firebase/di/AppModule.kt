package com.example.projectanroid.Firebase.di

import com.example.projectanroid.Firebase.conmon.contanst
import com.example.projectanroid.Firebase.data.FoodRepos
import com.example.projectanroid.Firebase.data.FoodReposllmt
import com.example.projectanroid.Firebase.data.Repository
import com.example.projectanroid.Firebase.data.RepositoryIlmt
import com.example.projectanroid.Firebase.data.StoreFirebasellmt
import com.example.projectanroid.Firebase.data.StorgeFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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

    @Provides
    @Singleton
    fun RepositoryDataStore(): StorageReference{
        return FirebaseStorage.getInstance().getReference()
    }

    @Provides
    @Singleton
    fun RepositoryDataStoreProvide( daStorageReference: StorageReference): StorgeFirebase{
        return StoreFirebasellmt(daStorageReference)
    }

}