package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.usecases.LogInUseCase
import com.example.cardfate.presentation.state.AuthError
import com.example.cardfate.presentation.state.AuthProgress
import com.example.cardfate.presentation.state.AuthState
import com.example.cardfate.presentation.state.AuthSuccess
import com.example.cardfate.domain.exceptions.UserDoesNotExistsException
import com.example.cardfate.domain.exceptions.WrongPasswordLogInException
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
) : ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun logIn(login: String, password: String) {
        val fieldsValid = validateInputLogIn(login, password)
        if (fieldsValid) {
            _authState.value = AuthProgress
            viewModelScope.launch {
                try {
                    logInUseCase.invoke(login, password) {
                        if (it != null)
                            if (it.password==password)
                                _authState.value = AuthSuccess(it.login)
                            else
                                _authState.value = AuthError(ERROR_WRONG_PASSWORD)
                        else
                            _authState.value = AuthError(ERROR_USER_DOES_NOT_EXISTS)
                    }
                } catch (e: UserDoesNotExistsException) {
                    _authState.value = AuthError(ERROR_USER_DOES_NOT_EXISTS)
                } catch (e: WrongPasswordLogInException) {
                    _authState.value = AuthError(ERROR_WRONG_PASSWORD)
                }
            }
        }
    }

    private fun validateInputLogIn(login: String, password: String): Boolean {
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
        const val ERROR_USER_DOES_NOT_EXISTS = 3
        const val ERROR_WRONG_PASSWORD = 4
    }
}