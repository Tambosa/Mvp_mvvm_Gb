package com.aroman.mvp_mvvm.domain

import androidx.annotation.WorkerThread

interface LoginApi {
    @WorkerThread
    fun login(login: String, password: String): Int

    @WorkerThread
    fun register(login: String, password: String): Int

    @WorkerThread
    fun logout(): Boolean

    @WorkerThread
    fun forgotPassword(login: String): Int
}