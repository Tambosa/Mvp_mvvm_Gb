package com.aroman.mvp_mvvm.domain

import com.aroman.mvp_mvvm.domain.entities.User

interface UserRepo {

    fun addUser(user: User)

    fun getUser(login: String): User

    fun getAllUsers(): List<User>

    fun updateUser(login: String, user: User)

    fun deleteUser(login: String)

    fun deleteAll()
}