package com.aroman.mvp_mvvm.domain

import androidx.annotation.MainThread

interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: (Int) -> Unit
    )

    fun register(
        login: String,
        password: String,
        @MainThread callback: (Int) -> Unit
    )

    fun forgotPassword(
        login: String,
        @MainThread callback: (Int) -> Unit
    )
}