package com.aroman.mvp_mvvm.ui

import com.aroman.mvp_mvvm.domain.LoginUsecase
import com.aroman.mvp_mvvm.domain.UserRepo
import com.aroman.mvp_mvvm.domain.entities.User

class LoginPresenter(private val loginUsecase: LoginUsecase, private val db: UserRepo) :
    LoginContract.LoginPresenter {
    private var view: LoginContract.LoginView? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""
    private var messageText: String = ""

    override fun onAttach(view: LoginContract.LoginView) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
            if (messageText != "") view.setMessage(messageText)
        } else {
            if (errorText != "") view.setError(errorText)
        }
    }

    override fun onLoginAttempt(login: String, password: String) {
        view?.showProgress()

        loginUsecase.login(login, password) { result ->
            when (result) {
                0 -> {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                    messageText = "Login successful"
                    view?.setMessage(messageText)
                }
                1 -> {
                    view?.setError("Login and password do not match")
                    isSuccess = false
                    errorText = "Login and password do not match"
                    messageText = ""
                }
            }
        }
    }

    override fun onRegisterNewUser(login: String, password: String) {
        view?.showProgress()
        loginUsecase.register(login, password) { result ->
            when (result) {
                0 -> {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                    messageText = "Login successful"
                    view?.setMessage(messageText)
                    db.addUser(User(login, password))
                }
                1 -> {
                    view?.setError("Login and password do not match")
                    isSuccess = false
                    errorText = "Login and password do not match"
                    messageText = ""
                }
            }
        }
    }

    override fun onForgotPassword(login: String) {
        view?.showProgress()
        loginUsecase.forgotPassword(login) { result ->
            when (result) {
                0 -> {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                    messageText = "Login successful"
                    view?.setMessage(messageText)
                    db.updateUser(login, User(login))
                }
                1 -> {
                    view?.setError("Login and password do not match")
                    isSuccess = false
                    errorText = "Login and password do not match"
                    messageText = ""
                }
            }
        }
    }
}