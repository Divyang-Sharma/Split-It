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

    /**
     * Returns transaction corresponding to expense synchronously
     */
    @Query("SELECT * FROM `Transaction` WHERE expenseId=:expenseId")
    fun getTransactionsForExpense(expenseId: Int): Flow<Transaction>

    /**
     * Returns transaction corresponding to expense synchronously
     */
    @Query("SELECT * FROM `Transaction` WHERE expenseId=:expenseId")
    fun getTransactionsForExpenseInSync(expenseId: Int): Transaction

    /**
     * Returns transaction to display
     */
    @Query("SELECT * FROM `Transaction` WHERE groupId=:groupId AND fromUser=:userId AND isPaid=1")
    fun getTransactionsForDisplay(groupId: Int,userId: Int): Flow<List<Transaction>>

}