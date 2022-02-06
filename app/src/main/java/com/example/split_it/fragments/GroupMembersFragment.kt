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
import com.example.split_it.database.model.Group
import com.example.split_it.database.model.User
import com.example.split_it.database.repository.GroupRepository
import com.example.split_it.database.repository.UserRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

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


        Log.i("Float","Before func")
        //Floating action button
        var add_button = groupMembersView?.findViewById<FloatingActionButton>(R.id.add_member_button)
        Log.i("Float","After func")
        //print(add_button)
        if (add_button != null) {
            Log.i("Hello","Hi")
            add_button.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
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
                            Log.i("Float","Inside OK")
                            val nameText = name.text.toString()
                            val emailText = email.text.toString()
                            if (!nameText.trim { it <= ' ' }.isEmpty()) {

                                //close the dialog
                                dialogInterface.dismiss()

                                //adding the current user to the member list

                                val memberId= System.currentTimeMillis().toInt()
                                //inserts the group into the db
                                val member =
                                    User(memberId, nameText, emailText,null)

                                groupRepository.getGroupForId(groupId).observe(activityContext as LifecycleOwner) {group->
                                    Log.i("Float","before group" + group.toString())

                                    group.users?.toMutableList()?.add(memberId)
                                    Log.i("Float","after group" + group.toString())

                                    groupRepository.updateGroup(group)
                                    userRepository.insertUser(member)
                                }
                            }
                        }
                        .show() // shows it | if not there it won't show
                }
            })
        }


        return groupMembersView
    }
}