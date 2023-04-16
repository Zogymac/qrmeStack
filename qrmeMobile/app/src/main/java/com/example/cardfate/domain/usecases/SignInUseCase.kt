package com.example.cardfate.domain.usecases

import com.example.cardfate.domain.repository.UsersRepository
import com.example.cardfate.domain.entity.User
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(login: String, password: String, callback: (String) -> Unit) =
        usersRepository.signIn(login, password, callback)
}