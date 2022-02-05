package com.example.split_it.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.split_it.database.model.Expense
import com.example.split_it.database.model.Transaction
import com.example.split_it.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao : AbstractDao<Expense> {

    /**
     * Returns all the expenses from the table
     */
    @Query("SELECT * FROM Expense")
    fun getExpenses(): Flow<List<Expense>>


    /**
     * Returns all the expenses for the given group
     */
    @Query("SELECT * FROM Expense WHERE groupId=:groupId")
    fun getExpensesForGroup(groupId : Int): Flow<List<Expense>>

}