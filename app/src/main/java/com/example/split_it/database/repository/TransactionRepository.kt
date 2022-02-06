package com.example.split_it.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.dao.TransactionDao
import com.example.split_it.database.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Middle layer between the UI and the database
 * Does the asynchronous operations.
 * In case of return value, returns livedata
 * Functions for `Transaction` table is defined in this class.
 */
class TransactionRepository(val database: AppDatabase) {

    var dao: TransactionDao = database.transactionDao()

    /**
     * Inserts transaction to the table asynchronously.
     */
    fun insertTransaction(transaction: Transaction) = CoroutineScope(Dispatchers.IO).launch {
        dao.insert(transaction)
    }

    /**
     * Updates transaction to the table asynchronously.
     */
    fun updateTransaction(transaction: Transaction) = CoroutineScope(Dispatchers.IO).launch {
        dao.update(transaction)
    }
    /**
     * Deletes transaction to the table asynchronously.
     */
    fun deleteTransaction(transaction: Transaction) = CoroutineScope(Dispatchers.IO).launch {
        dao.delete(transaction)
    }

    /**
     * Returns livedata of transactions.
     */
    fun getTransactions(): LiveData<List<Transaction>> {
        return dao.getTransactions().asLiveData()
    }

    /**
     * Returns transaction corresponding to expense
     */
    fun getTransactionsForExpense(expenseId: Int): LiveData<Transaction> {
        return dao.getTransactionsForExpense(expenseId).asLiveData()
    }

    /**
     * Returns transaction corresponding to expense
     */
    fun getTransactionsForExpenseInSync(expenseId: Int): Transaction {
        return dao.getTransactionsForExpenseInSync(expenseId)
    }

    /**
     * Returns transaction to display
     */
    fun getTransactionsForDisplay(groupId: Int,userId: Int): LiveData<List<Transaction>> {
        return dao.getTransactionsForDisplay(groupId,userId).asLiveData()
    }

}