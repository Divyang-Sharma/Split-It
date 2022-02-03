package com.example.split_it.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.split_it.database.model.Transaction
import com.example.split_it.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao : AbstractDao<Transaction> {

    /**
     * Returns all the users from the table
     */
    @Query("SELECT * FROM `Transaction`")
    fun getTransactions(): Flow<List<Transaction>>

}