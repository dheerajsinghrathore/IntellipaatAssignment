package com.android.intellipaat.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val username: String = "",
    val accessToken: String = ""
)
