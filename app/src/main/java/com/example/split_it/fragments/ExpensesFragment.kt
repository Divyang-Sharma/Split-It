package com.example.split_it.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.adapters.AdapterExpenses
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.model.Expense
import com.example.split_it.database.model.Transaction
import com.example.split_it.database.model.User
import com.example.split_it.database.repository.ExpenseRepository
import com.example.split_it.database.repository.GroupRepository
import com.example.split_it.database.repository.TransactionRepository
import com.example.split_it.database.repository.UserRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesFragment(val groupId: Int, val userId: Int) : Fragment() {
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
        val userRepository = UserRepository(database)
        val groupRepository = GroupRepository(database)
        val transactionRepository = TransactionRepository(database)


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

        //Floating action button
        val addButton = expensesView?.findViewById<FloatingActionButton>(R.id.add_member_button)
        //print(add_button)
        addButton?.setOnClickListener {

            // inflates the xml to the java object view
            val dialogView = layoutInflater.inflate(R.layout.layout_dialog_new_expense, null)
            val eventNameInput = dialogView.findViewById<EditText>(R.id.event_name_inut)
            val amountInput = dialogView.findViewById<EditText>(R.id.amount_input)
            val paidByUserSpinner = dialogView.findViewById<Spinner>(R.id.paid_by_spinner)

            CoroutineScope(Dispatchers.IO).launch {
                val group = groupRepository.getGroupForIdInSync(groupId)
                val users = userRepository.getUsersFromIdsInSync(group.users  ?: listOf())
                val currentUser = userRepository.getUserInSync(userId)
                val usersToDisplay = users.toMutableList()
                usersToDisplay.remove(currentUser)

                val userNameList = getUserNamesFromUsersExcludingCurrentUser(usersToDisplay)

                val arrayAdapter = ArrayAdapter(activityContext,android.R.layout.simple_expandable_list_item_1,userNameList)
                paidByUserSpinner.adapter = arrayAdapter

                val alertDialog = AlertDialog.Builder(context)
                    .setView(dialogView)
                    .setTitle("ENTER NEW EXPENSE")
                    .setNegativeButton(
                        "Cancel"
                    ) { dialogInterface: DialogInterface?, i: Int -> }
                    .setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
                        Log.i("Float", "Inside OK")
                        val eventName = eventNameInput.text.toString()
                        val amount = amountInput.text.toString()
                        val selectedUser = usersToDisplay[paidByUserSpinner.selectedItemPosition]

                        if (!eventName.trim { it <= ' ' }.isEmpty()) {

                            //close the dialog
                            dialogInterface.dismiss()

                            val expenseId = System.currentTimeMillis().toInt()

                            val expense = Expense(
                                expenseId,
                                groupId,
                                selectedUser?.id,
                                amount.toDouble(),
                                eventName
                            )

                            val transaction = Transaction(
                                null,
                                groupId,
                                expenseId,
                                userId,
                                selectedUser?.id,
                                amount.toDouble(),
                                eventName,
                                getDisplayStringForTransaction(currentUser,selectedUser,amount)
                            )

                            CoroutineScope(Dispatchers.IO).launch {
                                expenseRepository.insertExpense(expense)
                                transactionRepository.insertTransaction(transaction)
                            }

                        }
                    }

                CoroutineScope(Dispatchers.Main).launch {
                    alertDialog.show()
                }
            }


        }

        return expensesView
    }

    private fun getDisplayStringForTransaction(
        currentUser: User?,
        selectedUser: User?,
        amount: String
    ): String {
        return "You paid ${selectedUser?.name} Rs. $amount"
    }

    private fun getUserNamesFromUsersExcludingCurrentUser(users: List<User?>): ArrayList<String> {
        val userNameList = ArrayList<String>()

        for(user in users) {
            if(user?.id != userId) {
                userNameList.add(user?.name ?: "")
            }
        }
        return userNameList

    }

}