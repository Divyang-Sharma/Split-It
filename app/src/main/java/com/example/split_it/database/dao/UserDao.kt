package com.example.split_it.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.split_it.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : AbstractDao<User> {

    /**
     * Returns all the users from the table
     */
    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>


    /**
     * Returns user based on userId
     */
    @Query("SELECT * FROM User WHERE id=:userId")
    fun getUser(userId: Int?): Flow<User?>

    /**
     * Returns all the users from the table
     */
    @Query(
        "SELECT User.* FROM User Where id IN (:userIds)"
    )
    fun getUsersFromIds(userIds: List<Int>): Flow<List<User>>

}