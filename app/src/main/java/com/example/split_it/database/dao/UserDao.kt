package com.example.split_it.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.split_it.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : AbstractDao<User> {

    /**
     * Returns all the users from the table
     */
    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>

}