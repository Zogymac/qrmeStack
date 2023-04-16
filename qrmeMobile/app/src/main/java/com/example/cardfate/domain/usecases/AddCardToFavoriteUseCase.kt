package com.example.cardfate.domain.usecases

import com.example.cardfate.domain.repository.CardRepository
import javax.inject.Inject

class AddCardToFavoriteUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend operator fun invoke(userId: String, cardId: String, callback: () -> Unit) =
        cardRepository.addCartToFavorite(userId, cardId, callback)
}