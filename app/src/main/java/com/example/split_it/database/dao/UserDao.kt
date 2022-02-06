package com.example.split_it.database.dao

import androidx.room.Dao
import androidx.room.Query
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
     * Returns list of users from lsit of ids
     */
    @Query("SELECT User.* FROM User Where id IN (:userIds)")
    fun getUsersFromIds(userIds: List<Int>): Flow<List<User>>

    /**
     * Returns user if email exists or returns null
     */
    @Query("SELECT* FROM User WHERE email=:email")
    fun getUserFromEmail(email: String) : Flow<User?>


    /**
     * Returns user based on userId synchronously
     */
    @Query("SELECT * FROM User WHERE id IN (:userIds)")
    fun getUsersFromIdsInSync(userIds: List<Int>): List<User?>

    /**
     * Returns user based on userId
     */
    @Query("SELECT * FROM User WHERE id=:userId")
    fun getUserInSync(userId: Int?): User?

}