package com.example.split_it.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.adapters.AdapterExpenses
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.model.Expense
import com.example.split_it.database.repository.ExpenseRepository
import com.example.split_it.viewModels.ItemViewModelExpenses

class ExpensesFragment(val groupId: Int) : Fragment() {
    var expensesView : View? = null

    /**
     * Similar to onCreate in Activity
     * The respective layout is inflated and returned
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        expensesView = inflater.inflate(R.layout.fragment_expenses, container, false)

        val activityContext = requireContext()
        val database = AppDatabase.getDatabase(activityContext)
        val expenseRepository = ExpenseRepository(database)

        val recyclerview = expensesView?.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = LinearLayoutManager(context)

        // Gets the expense for group
        expenseRepository.getExpensesForGroup(groupId).observe(context as LifecycleOwner) { expenseList ->

            // This will pass the ArrayList to our Adapter
            val expenseAdapter = AdapterExpenses(activityContext, expenseList, database)

            // Setting the Adapter with the recyclerview
            recyclerview?.adapter = expenseAdapter
        }

        return expensesView
    }

}