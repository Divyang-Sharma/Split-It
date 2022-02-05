package com.example.split_it.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.adapters.AdapterExpenses
import com.example.split_it.adapters.AdapterMembers
import com.example.split_it.viewModels.ItemViewModelExpenses
import com.example.split_it.viewModels.ItemViewModelMembers
import org.w3c.dom.Text

class GroupMembersFragment : Fragment() {

    var groupMembersView : View? = null

    /**
     * Similar to onCreate in Activity
     * The respective layout is inflated and returned
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        groupMembersView = inflater.inflate(R.layout.fragment_group_members, container, false)

        //TODO: logic of groupMembers goes here
        val recyclerview = groupMembersView?.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModelMembers>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModelMembers(R.drawable.common_google_signin_btn_icon_dark, "Gabbar Singh"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = AdapterMembers(data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter
        //use findViewById with view `expensesView?.findViewById<>()`

        //use findViewById with view `groupMembersView?.findViewById<>()`

        return groupMembersView
    }
}