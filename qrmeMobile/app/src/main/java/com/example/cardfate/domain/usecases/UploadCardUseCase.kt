package com.example.cardfate.domain.usecases

import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.repository.CardRepository
import javax.inject.Inject

class UploadCardUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend operator fun invoke(card: Card, callback: () -> Unit) =
        cardRepository.uploadCard(card, callback)
}