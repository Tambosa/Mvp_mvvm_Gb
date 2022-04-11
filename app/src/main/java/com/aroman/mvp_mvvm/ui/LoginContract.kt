package com.aroman.mvp_mvvm.ui

import androidx.annotation.MainThread
import com.aroman.mvp_mvvm.utils.Publisher

class LoginContract {
    interface ViewModel {
        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        @MainThread
        fun onLoginAttempt(login: String, password: String)

        @MainThread
        fun onRegisterNewUser(login: String, password: String)

        @MainThread
        fun onForgotPassword(login: String)
    }
}