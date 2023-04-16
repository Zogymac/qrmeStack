package com.example.cardfate.domain.usecases

import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.repository.CardRepository
import javax.inject.Inject

class GetCardByIdUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend operator fun invoke(cardId: String, callback: (Card) -> Unit) =
        cardRepository.getCardById(cardId, callback)
}