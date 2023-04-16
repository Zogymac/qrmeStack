package com.example.cardfate.domain.usecases

import com.example.cardfate.domain.entity.User
import com.example.cardfate.domain.repository.UsersRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(name: String, password: String, callback: (User?) -> Unit) =
        usersRepository.logIn(name, password, callback)
}