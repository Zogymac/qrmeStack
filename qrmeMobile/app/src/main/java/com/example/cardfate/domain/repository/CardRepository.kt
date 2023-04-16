package com.example.cardfate.domain.repository

import com.example.cardfate.domain.entity.Card

interface CardRepository {

    suspend fun uploadCard(card: Card, callback: () -> Unit)

    suspend fun getCardsByUserId(userId: String, callback: (List<Card>) -> Unit)

    suspend fun getCardById(cardId: String, callback: (Card) -> Unit)

    suspend fun addCartToFavorite(userId: String, cardId: String, callback: () -> Unit)

    suspend fun getFavoriteCardsByUserId(userId: String, callback: (List<Card>) -> Unit)
}