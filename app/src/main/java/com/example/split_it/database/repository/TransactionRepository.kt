package com.example.split_it.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.dao.TransactionDao
import com.example.split_it.database.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
}