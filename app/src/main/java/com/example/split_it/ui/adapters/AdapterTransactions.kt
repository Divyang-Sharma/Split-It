package com.example.split_it.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.database.model.Transaction

class AdapterTransactions(
    private val transactionList: List<Transaction>
) : RecyclerView.Adapter<AdapterTransactions.TransactionViewHolder>() {

    // Holds the views for adding it to image and text
    class TransactionViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val transactionDisplayText: TextView = itemView.findViewById(R.id.transaction_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_transactions, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.transactionDisplayText.text = transactionList[position].displayString
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }
}