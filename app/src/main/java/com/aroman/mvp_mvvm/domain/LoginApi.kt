package com.aroman.mvp_mvvm.domain

interface LoginApi {
    fun login(login: String, password: String): Int
    fun register(login: String, password: String): Int
    fun logout(): Boolean
    fun forgotPassword(login: String): Int
}