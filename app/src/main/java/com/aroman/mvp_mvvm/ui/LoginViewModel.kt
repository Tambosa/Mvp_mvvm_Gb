package com.aroman.mvp_mvvm.ui

import com.aroman.mvp_mvvm.domain.LoginUsecase
import com.aroman.mvp_mvvm.domain.UserRepo
import com.aroman.mvp_mvvm.domain.entities.User
import com.aroman.mvp_mvvm.utils.Publisher

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
                }
                2 -> {
                    isSuccess.post(false)
                    errorText.post("Password too short")
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
                }
            }
        }
    }
}