package com.aroman.mvp_mvvm.data

import com.aroman.mvp_mvvm.domain.LoginApi
import kotlin.random.Random

class MockLoginApiImpl : LoginApi {
    override fun login(login: String, password: String): Int {
        return Random.nextInt(0, 2)
    }

    override fun register(login: String, password: String): Int {
        return Random.nextInt(0, 3)
    }

    override fun logout(): Boolean {
        return Random.nextBoolean()
    }

    override fun forgotPassword(login: String): Int {
        return Random.nextInt(0, 2)
    }
}