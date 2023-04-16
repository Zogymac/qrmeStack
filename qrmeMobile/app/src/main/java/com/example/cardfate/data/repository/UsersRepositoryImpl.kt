package com.example.cardfate.data.repository

import com.example.cardfate.domain.entity.User
import com.example.cardfate.domain.exceptions.*
import com.example.cardfate.domain.repository.UsersRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : UsersRepository {

    override suspend fun signIn(login: String, password: String, callback: (String) -> Unit) {
        val user = User(login = login, password = password)
        db.collection(USERS)
            .document(login)
            .set(user)
            .addOnSuccessListener {
                callback.invoke(login)
            }
            .addOnFailureListener {
                if (it is FirebaseFirestoreException)
                    throw UserAlreadyExistsException()
                else
                    throw CheckInternetException()
            }
    }

    override suspend fun logIn(login: String, password: String, callback: (User?) -> Unit) {
        db.collection(USERS).document(login)
            .get()
            .addOnSuccessListener {
                if (it != null) {
                    val user = it.toObject<User>()
                    callback.invoke(user)
                } else {
                    throw UserDoesNotExistsException()
                }
            }
            .addOnFailureListener {
                throw CheckInternetException()
            }
    }


    companion object {

        const val USERS = "users"
    }
}