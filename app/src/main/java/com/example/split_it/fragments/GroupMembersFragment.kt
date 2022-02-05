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
import com.example.split_it.adapters.AdapterMembers
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.repository.GroupRepository
import com.example.split_it.database.repository.UserRepository

class GroupMembersFragment(
    val groupId: Int
) : Fragment() {

    var groupMembersView: View? = null

    /**
     * Similar to onCreate in Activity
     * The respective layout is inflated and returned
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        groupMembersView = inflater.inflate(R.layout.fragment_group_members, container, false)


        val activityContext = requireContext()
        val database = AppDatabase.getDatabase(activityContext)
        val userRepository = UserRepository(database)
        val groupRepository = GroupRepository(database)

        val recyclerview = groupMembersView?.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = LinearLayoutManager(context)

        // Gets the group
        groupRepository.getGroupForId(groupId).observe(context as LifecycleOwner) { group ->

            // Gets the list of users for the current group
            userRepository.getUsersForGroup(group.users ?: listOf())
                .observe(context as LifecycleOwner) { membersList ->
                    // This will pass the ArrayList to our Adapter
                    val adapter = AdapterMembers(activityContext, membersList)

                    // Setting the Adapter with the recyclerview
                    recyclerview?.adapter = adapter
                }
        }



        return groupMembersView
    }
}