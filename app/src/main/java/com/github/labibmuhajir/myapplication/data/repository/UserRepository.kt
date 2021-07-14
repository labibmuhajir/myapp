package com.github.labibmuhajir.myapplication.data.repository

import com.github.labibmuhajir.myapplication.data.local.PrefManager
import com.github.labibmuhajir.myapplication.data.model.User


interface UserDataSource {
    suspend fun getUser(): User?
    suspend fun setUser(user: User)
    suspend fun logout()
}

class UserRepository(private val prefManager: PrefManager) : UserDataSource {
    override suspend fun getUser(): User? = prefManager.user
    override suspend fun setUser(user: User) {
        prefManager.user = user
    }

    override suspend fun logout() {
        prefManager.clear()
    }
}