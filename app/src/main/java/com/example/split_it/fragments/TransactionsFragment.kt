package com.example.split_it.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.split_it.R

class TransactionsFragment(groupId: Int) : Fragment() {

    var transactionsView : View? = null

    /**
     * Similar to onCreate in Activity
     * The respective layout is inflated and returned
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        transactionsView = inflater.inflate(R.layout.fragment_group_members, container, false)

        //TODO: logic of transactions goes here

        //use findViewById with view `transactionsView?.findViewById<>()`

        return transactionsView
    }

}