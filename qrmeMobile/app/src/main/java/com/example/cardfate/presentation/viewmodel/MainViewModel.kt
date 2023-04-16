package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.usecases.GetCardsByUserIdUseCase
import com.example.cardfate.domain.usecases.LogInUseCase
import com.example.cardfate.domain.usecases.UploadCardUseCase
import com.example.cardfate.presentation.state.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCardsByUserIdUseCase: GetCardsByUserIdUseCase,
) : ViewModel() {

    private var _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    fun getCardsByUserId(userId: String) {
        viewModelScope.launch {
            getCardsByUserIdUseCase.invoke(userId) {
                try {
                    _cards.value = it
                } catch (_: Exception) {
                }
            }
        }
    }
}