package com.aroman.mvp_mvvm

import android.app.Application
import android.content.Context
import com.aroman.mvp_mvvm.data.DbUserRepo
import com.aroman.mvp_mvvm.data.LoginUsecaseImpl
import com.aroman.mvp_mvvm.data.MockLoginApiImpl
import com.aroman.mvp_mvvm.domain.LoginApi

class App : Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase by lazy { LoginUsecaseImpl(app.loginApi) }
    val dbUserRepo by lazy { DbUserRepo() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }