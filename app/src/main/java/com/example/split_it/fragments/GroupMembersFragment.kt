package com.example.split_it.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.split_it.R
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

        //use findViewById with view `groupMembersView?.findViewById<>()`

        return groupMembersView
    }
}