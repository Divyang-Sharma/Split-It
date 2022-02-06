package com.example.split_it.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.adapters.AdapterMembers
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.model.User
import com.example.split_it.database.repository.GroupRepository
import com.example.split_it.database.repository.UserRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


        //Floating action button
        val addButton = groupMembersView?.findViewById<FloatingActionButton>(R.id.add_member_button)
        //print(add_button)
        addButton?.setOnClickListener {

            // inflates the xml to the java object view
            val dialogView = layoutInflater.inflate(R.layout.layout_dialog_new_member, null)
            val name = dialogView.findViewById<EditText>(R.id.name)
            val email = dialogView.findViewById<EditText>(R.id.email)

            AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("ENTER NEW MEMBER")
                .setNegativeButton(
                    "Cancel"
                ) { dialogInterface: DialogInterface?, i: Int -> }
                .setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
                    Log.i("Float", "Inside OK")
                    val nameText = name.text.toString()
                    val emailText = email.text.toString()
                    if (!nameText.trim { it <= ' ' }.isEmpty()) {

                        //close the dialog
                        dialogInterface.dismiss()

                        //adding the current user to the member list
                        val memberId = System.currentTimeMillis().toInt()
                        val member = User(memberId, nameText, emailText, null)

                        //inserts the group into the db | using coroutine to prevent loop of livedata
                        CoroutineScope(Dispatchers.IO).launch {
                            val group = groupRepository.getGroupForIdInSync(groupId)

                            // copies the list to mutable
                            val usersCopy = group.users?.toMutableList()

                            // adds the memberId
                            usersCopy?.add(memberId)

                            // reassigning new userList to the group
                            group.users = usersCopy?.toList()

                            // updating the database
                            groupRepository.updateGroup(group)
                            userRepository.insertUser(member)
                        }


                    }
                }
                .show() // shows it | if not there it won't show
        }



        return groupMembersView
    }
}