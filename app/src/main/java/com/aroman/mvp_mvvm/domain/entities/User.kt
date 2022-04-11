package com.aroman.mvp_mvvm.domain.entities

data class User(
    val login: String,
    val password: String = "1234",
)