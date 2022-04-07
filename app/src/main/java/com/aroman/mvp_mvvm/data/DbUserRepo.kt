package com.aroman.mvp_mvvm.data

import com.aroman.mvp_mvvm.domain.UserRepo
import com.aroman.mvp_mvvm.domain.entities.User

class DbUserRepo : UserRepo {
    override fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUser(login: String): User {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun updateUser(login: String, user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(login: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}