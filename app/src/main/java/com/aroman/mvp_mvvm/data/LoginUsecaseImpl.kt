package com.aroman.mvp_mvvm.data

import android.os.Handler
import com.aroman.mvp_mvvm.domain.LoginApi
import com.aroman.mvp_mvvm.domain.LoginUsecase

class LoginUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUsecase {
    override fun login(login: String, password: String, callback: (Int) -> Unit) {
        Thread {
            Thread.sleep(2_000)
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun register(login: String, password: String, callback: (Int) -> Unit) {
        Thread {
            Thread.sleep(2_000)
            val result = api.register(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun forgotPassword(login: String, callback: (Int) -> Unit) {
        Thread {
            Thread.sleep(2_000)
            val result = api.forgotPassword(login)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}