package com.example.projectanroid.data.Firebase.di

import com.example.projectanroid.data.Repository.FoodRepos
import com.example.projectanroid.data.Repository.FoodReposllmt
import com.example.projectanroid.data.Repository.Repository
import com.example.projectanroid.data.Repository.RepositoryIlmt
import com.example.projectanroid.data.Repository.StoreFirebasellmt
import com.example.projectanroid.data.Repository.StorgeFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
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
    fun RepositoryllmtProvide(firebaseAuth: FirebaseAuth): Repository {
        return RepositoryIlmt(firebaseAuth)

    }
    @Provides
    @Singleton
    fun FirebasDataProvide ():DatabaseReference{
       return Firebase.database.getReference("Foododer")
    }

    @Provides
    @Singleton
    fun RepositoryFirebaseDatallmtProvide(databaseReference: DatabaseReference): FoodRepos {
        return FoodReposllmt(databaseReference)

    }

    @Provides
    @Singleton
    fun RepositoryDataStore(): StorageReference{
        return FirebaseStorage.getInstance().getReference()
    }

    @Provides
    @Singleton
    fun RepositoryDataStoreProvide( daStorageReference: StorageReference): StorgeFirebase {
        return StoreFirebasellmt(daStorageReference)
    }

}