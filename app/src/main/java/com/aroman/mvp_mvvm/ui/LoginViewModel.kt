package com.aroman.mvp_mvvm.ui

import com.aroman.mvp_mvvm.domain.LoginUsecase
import com.aroman.mvp_mvvm.domain.UserRepo
import com.aroman.mvp_mvvm.domain.entities.User
import com.aroman.mvp_mvvm.utils.Publisher
import java.lang.Thread.sleep

class LoginViewModel(private val loginUsecase: LoginUsecase, private val db: UserRepo) :
    LoginContract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher(true)

    override fun onLoginAttempt(login: String, password: String) {
        shouldShowProgress.post(true)

        loginUsecase.login(login, password) { result ->
            shouldShowProgress.post(false)
            when (result) {
                0 -> {
                    errorText.post("")
                    isSuccess.post(true)
                }
                1 -> {
                    isSuccess.post(false)
                    errorText.post("Login and password do not match")
                    Thread {
                        sleep(2_000)
                        errorText.post("")
                    }.run()
                }
            }
        }
    }

    override fun onRegisterNewUser(login: String, password: String) {
        shouldShowProgress.post(true)
        loginUsecase.register(login, password) { result ->
            shouldShowProgress.post(false)
            when (result) {
                0 -> {
                    errorText.post("")
                    isSuccess.post(true)
                }
                1 -> {
                    isSuccess.post(false)
                    errorText.post("Login is already taken")
                    Thread {
                        sleep(2_000)
                        errorText.post("")
                    }.run()
                }
                2 -> {
                    isSuccess.post(false)
                    errorText.post("Password too short")
                    Thread {
                        sleep(2_000)
                        errorText.post("")
                    }.run()
                }
            }
        }
    }

    override fun onForgotPassword(login: String) {
        shouldShowProgress.post(true)
        loginUsecase.forgotPassword(login) { result ->
            shouldShowProgress.post(false)
            when (result) {
                0 -> {
                    errorText.post("")
                    isSuccess.post(true)
                }
                1 -> {
                    isSuccess.post(false)
                    errorText.post("User not found")
                    Thread {
                        sleep(2_000)
                        errorText.post("")
                    }.run()
                }
            }
        }
    }
}