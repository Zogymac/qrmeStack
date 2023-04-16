package com.example.cardfate.data.repository

import android.net.Uri
import android.util.Log
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.entity.User
import com.example.cardfate.domain.exceptions.*
import com.example.cardfate.domain.repository.CardRepository
import com.example.cardfate.domain.repository.UsersRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CardRepository {

    override suspend fun uploadCard(card: Card, callback: () -> Unit) {
        val id = db.collection(CARDS).document().id

        if (card.imageUrl != null) {
            val storageReference = Firebase.storage.reference
            val fileRef = storageReference.child("cards/images/${id}.jpg")
            val imageUri = Uri.parse(card.imageUrl)

            fileRef.putFile(imageUri)
                .addOnSuccessListener {
                    Log.d("muri", "File Uploaded Successfully")

                    val docRef = db.collection(CARDS).document(id)
                    fileRef.downloadUrl.addOnSuccessListener {
                        val cardDto = card.copy(imageUrl = it.toString(), id = id)
                        docRef.set(cardDto)
                            .addOnSuccessListener {
                                callback.invoke()
                            }
                            .addOnFailureListener {
                                throw CheckInternetException()
                            }
                    }
                }
                .addOnFailureListener {
                    throw CheckInternetException()
                }
        } else {
            val docRef = db.collection(CARDS).document(id)
            val cardDto = card.copy(id = id)
            docRef.set(cardDto)
                .addOnSuccessListener {
                    callback.invoke()
                }
                .addOnFailureListener {
                    throw CheckInternetException()
                }
        }
    }

    override suspend fun getCardsByUserId(userId: String, callback: (List<Card>) -> Unit) {
        db.collection(CARDS)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        val cardsDto = task.result.documents
                            .map { it.toObject<Card>()!! }
                            .filter { it.userId == userId }
                        callback.invoke(cardsDto)
                    } catch (_: Exception) {
                    }
                } else {
                    throw CheckInternetException()
                }
            }
    }

    override suspend fun getCardById(cardId: String, callback: (Card) -> Unit) {
        db.collection(CARDS).document(cardId)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    val cardDto = it.toObject<Card>()
                    callback.invoke(cardDto!!)
                }
            }
            .addOnFailureListener {
                throw CheckInternetException()
            }
    }

    override suspend fun addCartToFavorite(userId: String, cardId: String, callback: () -> Unit) {
        db.collection(UsersRepositoryImpl.USERS).document(userId)
            .collection(FAVORITE_CARDS)
            .document(cardId)
            .set(hashMapOf("cardId" to cardId))
            .addOnSuccessListener {
                callback.invoke()
            }
    }

    override suspend fun getFavoriteCardsByUserId(userId: String, callback: (List<Card>) -> Unit) {
        db.collection(UsersRepositoryImpl.USERS)
            .document(userId)
            .collection("favorite_cards")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        val cardsIds = task.result.documents.map { it.get("cardId") }
                        val cardsDto = listOf<Card>()
                        db.collection(CARDS).document(cardsIds[0].toString())
                            .get()
                            .addOnSuccessListener {
                                if (it != null) {
                                    val cardDto = it.toObject<Card>()
                                    callback.invoke(listOf(cardDto!!))
                                }
                            }
                    } catch (_: Exception) {
                    }
                }
            }
    }

    companion object {

        const val CARDS = "cards"
        const val FAVORITE_CARDS = "favorite_cards"
    }
}