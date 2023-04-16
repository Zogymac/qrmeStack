package com.example.cardfate.domain.repository

import com.example.cardfate.domain.entity.User

interface UsersRepository {

    suspend fun signIn(login: String, password: String, callback: (String) -> Unit)

    suspend fun logIn(login: String, password: String, callback: (User?) -> Unit)
}