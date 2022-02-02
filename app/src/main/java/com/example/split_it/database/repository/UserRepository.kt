package com.example.split_it.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.dao.UserDao
import com.example.split_it.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Middle layer between the UI and the database
 * Does the asynchronous operations.
 * In case of return value, returns livedata
 * Functions for `User` table is defined in this class.
 */
class UserRepository(val database: AppDatabase) {

    var dao: UserDao = database.userDao()

    /**
     * Inserts user to the table asynchronously.
     */
    fun insertUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        dao.insert(user)
    }

    /**
     * Updates user to the table asynchronously.
     */
    fun updateUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        dao.update(user)
    }
    /**
     * Deletes user to the table asynchronously.
     */
    fun deleteUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        dao.delete(user)
    }

    /**
     * Returns livedata of users.
     */
    fun getUsers(): LiveData<List<User>> {
        return dao.getUsers().asLiveData()
    }
}