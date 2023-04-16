package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.usecases.SignInUseCase
import com.example.cardfate.presentation.state.AuthError
import com.example.cardfate.presentation.state.AuthProgress
import com.example.cardfate.presentation.state.AuthState
import com.example.cardfate.presentation.state.AuthSuccess
import com.example.cardfate.domain.exceptions.UserAlreadyExistsException
import com.example.cardfate.domain.entity.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun signIn(login: String, password: String) {
        val fieldsValid = validateInputSignIn(login, password)
        if (fieldsValid) {
            _authState.value = AuthProgress
            val user = User(login = login, password = password)
            viewModelScope.launch {
                try {
                    signInUseCase.invoke(login,password) {
                        _authState.value = AuthSuccess(it)
                    }
                } catch (e: UserAlreadyExistsException) {
                    _authState.value = AuthError(ERROR_SUCH_USER_EXISTS)
                }
            }
        }
    }

    private fun validateInputSignIn(
        login: String,
        password: String,
    ): Boolean {
        if (login.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_LOGIN)
            return false
        }
        if (password.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_PASSWORD)
            return false
        }
        return true
    }

    companion object {

        const val ERROR_EMPTY_LOGIN = 1
        const val ERROR_EMPTY_PASSWORD = 2
        const val ERROR_SUCH_USER_EXISTS = 3
    }
}