package com.example.split_it.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.viewModels.ItemViewModelExpenses

class AdapterExpenses(private val mList: List<ItemViewModelExpenses>) : RecyclerView.Adapter<AdapterExpenses.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_expenses, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.eventView.text = ItemsViewModel.event
        holder.costView.text = ItemsViewModel.Cost.toString()
        holder.paidByView.text = ItemsViewModel.paid_by
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val eventView: TextView = itemView.findViewById(R.id.event_text_view)
        val paidByView: TextView = itemView.findViewById(R.id.paid_by_text_view)
        val costView: TextView = itemView.findViewById(R.id.cost_text_view)
    }
}