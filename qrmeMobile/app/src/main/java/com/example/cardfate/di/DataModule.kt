package com.example.cardfate.di

import com.example.cardfate.data.repository.CardRepositoryImpl
import com.example.cardfate.data.repository.UsersRepositoryImpl
import com.example.cardfate.domain.repository.CardRepository
import com.example.cardfate.domain.repository.UsersRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    fun bindFirebaseUsersRepository(impl: UsersRepositoryImpl): UsersRepository

    @Binds
    fun bindFirebaseCardRepository(impl: CardRepositoryImpl): CardRepository

    companion object {

        @Provides
        fun provideFirebaseDb(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }
}