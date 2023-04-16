package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.usecases.*
import com.example.cardfate.presentation.state.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardViewModel @Inject constructor(
    private val getCardById: GetCardByIdUseCase,
    private val addCardToFavorite: AddCardToFavoriteUseCase,
) : ViewModel() {

    private var _card = MutableLiveData<Card>()
    val card: LiveData<Card> = _card

    private var _cardAdded = MutableLiveData<Unit>()
    val cardAdded: LiveData<Unit> = _cardAdded

    fun getCardById(cardId: String) {
        viewModelScope.launch {
            getCardById.invoke(cardId) {
                try {
                    _card.value = it
                } catch (_: Exception) {
                }
            }
        }
    }

    fun addCardToFavorite(userId: String, cardId: String){
        viewModelScope.launch {
            addCardToFavorite.invoke(userId, cardId) {
                _cardAdded.value = Unit
            }
        }
    }
}