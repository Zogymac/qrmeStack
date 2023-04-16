package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.usecases.GetCardsByUserIdUseCase
import com.example.cardfate.domain.usecases.GetFavoriteCardsByUserIdUseCase
import com.example.cardfate.domain.usecases.LogInUseCase
import com.example.cardfate.domain.usecases.UploadCardUseCase
import com.example.cardfate.presentation.state.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoriteCardsByUserIdUseCase: GetFavoriteCardsByUserIdUseCase,
) : ViewModel() {

    private var _favoriteCards = MutableLiveData<List<Card>>()
    val favoriteCards: LiveData<List<Card>> = _favoriteCards

    fun getFavoriteCardsByUserId(userId: String) {
        viewModelScope.launch {
            getFavoriteCardsByUserIdUseCase.invoke(userId) {
                try {
                    _favoriteCards.value = it
                } catch (_: Exception) {
                }
            }
        }
    }
}