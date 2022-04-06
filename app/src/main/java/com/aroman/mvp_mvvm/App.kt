package com.aroman.mvp_mvvm

import android.app.Application
import android.content.Context
import com.aroman.mvp_mvvm.data.MockLoginApiImpl
import com.aroman.mvp_mvvm.domain.LoginApi

class App : Application() {
    val api: LoginApi by lazy { MockLoginApiImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }