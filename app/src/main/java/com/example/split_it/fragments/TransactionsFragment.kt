package com.example.split_it.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.adapters.AdapterTransactions
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.repository.TransactionRepository

class TransactionsFragment(
    val groupId: Int,
    val userId: Int,
) : Fragment() {

    var transactionsView: View? = null

    /**
     * Similar to onCreate in Activity
     * The respective layout is inflated and returned
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        transactionsView = inflater.inflate(R.layout.fragment_group_members, container, false)

        val activityContext = requireContext()
        val database = AppDatabase.getDatabase(activityContext)
        val transactionRepository = TransactionRepository(database)

        val recyclerview = transactionsView?.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview?.layoutManager = LinearLayoutManager(context)

        transactionRepository.getTransactionsForDisplay(groupId,userId).observe(activityContext as LifecycleOwner) { transactionList ->
            val transactionAdapter = AdapterTransactions(transactionList)
            recyclerview?.adapter = transactionAdapter
        }



        return transactionsView
    }

}