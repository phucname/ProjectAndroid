package com.example.projectanroid.Firebase.di

import com.example.projectanroid.Firebase.data.Repository
import com.example.projectanroid.Firebase.data.RepositoryIlmt
import com.google.firebase.auth.FirebaseAuth
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

}