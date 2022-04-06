package com.aroman.mvp_mvvm

import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import java.lang.Thread.sleep

class LoginPresenter : LoginContract.LoginPresenter {
    private var view: LoginContract.LoginView? = null
    private val model: LoginContract.LoginModel = LoginModel()
    private val uiHandler = Handler(Looper.getMainLooper())
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
        Thread {
            sleep(2_000)
            uiHandler.post {
                when (model.onLoginAttempt(login, password)) {
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
        }.start()
    }

    override fun onRegisterNewUser(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(2_000)
            uiHandler.post {
                when (model.onCreateNewUser(login, password)) {
                    0 -> {
                        view?.setSuccess()
                        isSuccess = true
                        errorText = ""
                        messageText = "Registration successful"
                        view?.setMessage(messageText)
                    }
                    1 -> {
                        view?.setError("Username already taken")
                        isSuccess = false
                        errorText = "Username already taken"
                        messageText = ""
                    }
                    2 -> {
                        view?.setError("Password too short")
                        isSuccess = false
                        errorText = "Password too short"
                        messageText = ""
                    }
                }
            }
        }.start()
    }

    override fun onForgotPassword(login: String) {
        view?.showProgress()
        Thread {
            sleep(2_000)
            uiHandler.post {
                when (model.onForgotPassword(login)) {
                    0 -> {
                        view?.setSuccess()
                        isSuccess = true
                        errorText = ""
                        messageText = "New password is sent to your email"
                        view?.setMessage(messageText)
                    }
                    1 -> {
                        view?.setError("Username not found")
                        isSuccess = false
                        errorText = "Username not found"
                        messageText = ""
                    }
                }
            }
        }.start()
    }
}