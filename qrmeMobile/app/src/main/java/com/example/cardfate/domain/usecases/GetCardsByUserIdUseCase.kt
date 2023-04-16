package com.example.cardfate.domain.usecases

import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.repository.CardRepository
import javax.inject.Inject

class GetCardsByUserIdUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend operator fun invoke(userId: String, callback: (List<Card>) -> Unit) =
        cardRepository.getCardsByUserId(userId, callback)
}