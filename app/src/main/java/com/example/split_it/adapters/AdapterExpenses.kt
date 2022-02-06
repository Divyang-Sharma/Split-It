package com.example.split_it.adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.model.Expense
import com.example.split_it.database.repository.UserRepository

class AdapterExpenses(
    private val activityContext: Context,
    private val expenseList: List<Expense>,
    database: AppDatabase
) : RecyclerView.Adapter<AdapterExpenses.ExpensesViewHolder>() {

    private val userRepository: UserRepository = UserRepository(database)

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_expenses, parent, false)
        return ExpensesViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        val expense = expenseList[position]

        holder.eventView.text = expense.topicOfExpense
        holder.costView.text = expense.amount.toString()

        userRepository.getUser(expense.paidByUser).observe(activityContext as LifecycleOwner) {
           user -> holder.paidByView.text = user?.name
        }

        holder.payNowButton.setOnClickListener { v->
            // pay now functionality
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return expenseList.size
    }

    // Holds the views for adding it to image and text
    class ExpensesViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val eventView: TextView = itemView.findViewById(R.id.event_text_view)
        val paidByView: TextView = itemView.findViewById(R.id.paid_by_text_view)
        val costView: TextView = itemView.findViewById(R.id.cost_text_view)
        val payNowButton: Button = itemView.findViewById(R.id.pay_button)
    }
}