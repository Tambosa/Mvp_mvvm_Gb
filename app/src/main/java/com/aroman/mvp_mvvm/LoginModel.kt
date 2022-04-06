package com.aroman.mvp_mvvm

import kotlin.random.Random

class LoginModel : LoginContract.LoginModel {
    override fun onLoginAttempt(login: String, password: String): Int {
        //идем на сервер за ответом
        return Random.nextInt(0, 2)
    }

    override fun onCreateNewUser(login: String, password: String): Int {
        //идем на сервер за ответом
        return Random.nextInt(0, 3)
    }

    override fun onForgotPassword(login: String): Int {
        //идем на сервер за ответом
        return Random.nextInt(0, 2)
    }
}