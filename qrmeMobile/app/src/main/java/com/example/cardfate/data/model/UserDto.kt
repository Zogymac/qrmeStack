package com.example.cardfate.data.model

data class UserDto(
    val id: Int? = null,
    val photoUrl: String? = null,
    val login: String = "",
    val password: String = "",
    val fullName: String? = null,
    val bio: String? = null,
    val qrUrl: String? = null,
    val idLink: Int? = null,
    val idCard: Int? = null,
    val idSkill: Int? = null,
)
