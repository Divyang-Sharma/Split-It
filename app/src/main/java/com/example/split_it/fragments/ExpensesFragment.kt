package com.example.split_it.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.adapters.AdapterExpenses
import com.example.split_it.viewModels.ItemViewModelExpenses

class ExpensesFragment : Fragment() {
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

        //TODO: logic of expenses goes here
        // getting the recyclerview by its id
        val recyclerview = expensesView?.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModelExpenses>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModelExpenses("Lunch At Mall", 310,"Gabbar Singh"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = AdapterExpenses(data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter
        //use findViewById with view `expensesView?.findViewById<>()`

        return expensesView
    }

}