package com.example.split_it.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.dao.ExpenseDao
import com.example.split_it.database.model.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Middle layer between the UI and the database
 * Does the asynchronous operations.
 * In case of return value, returns livedata
 * Functions for `Expense` table is defined in this class.
 */
class ExpenseRepository(val database: AppDatabase) {

    var dao: ExpenseDao = database.expenseDao()

    /**
     * Inserts expense to the table asynchronously.
     */
    fun insertExpense(expense: Expense) = CoroutineScope(Dispatchers.IO).launch {
        dao.insert(expense)
    }

    /**
     * Updates expense to the table asynchronously.
     */
    fun updateExpense(expense: Expense) = CoroutineScope(Dispatchers.IO).launch {
        dao.update(expense)
    }
    /**
     * Deletes expense to the table asynchronously.
     */
    fun deleteExpense(expense: Expense) = CoroutineScope(Dispatchers.IO).launch {
        dao.delete(expense)
    }

    /**
     * Returns livedata of expenses.
     */
    fun getExpenses(): LiveData<List<Expense>> {
        return dao.getExpenses().asLiveData()
    }
}