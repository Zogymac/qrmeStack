package com.example.cardfate.presentation.state

sealed class AuthState()

object AuthProgress : AuthState()
class AuthError(val errorCode: Int): AuthState()
class AuthSuccess(val login: String): AuthState()
