package com.aroman.mvp_mvvm

import androidx.annotation.MainThread

class LoginContract {

    interface LoginView {
        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun setMessage(message: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()
    }

    interface LoginPresenter {
        fun onAttach(view: LoginView)
        fun onLoginAttempt(login: String, password: String)
        fun onRegisterNewUser(login: String, password: String)
        fun onForgotPassword(login: String)
    }

    interface LoginModel {
        fun onLoginAttempt(login: String, password: String): Int
        fun onCreateNewUser(login: String, password: String): Int
        fun onForgotPassword(login: String): Int
    }
}