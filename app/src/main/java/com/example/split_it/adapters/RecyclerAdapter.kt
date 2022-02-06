package com.example.split_it.adapters
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemMemberCount: TextView
        var itemDate: TextView

        init{
            itemTitle=itemView.findViewById(R.id.card_title)
            itemMemberCount=itemView.findViewById(R.id.card_memberCount)
            itemDate=itemView.findViewById(R.id.card_date)
        }
    }
}