package com.example.specialnote.repository

import androidx.lifecycle.LiveData
import com.example.specialnote.data.UserDao
import com.example.specialnote.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData:LiveData<List<User>> = userDao.readAllData()

   suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user:User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }




}