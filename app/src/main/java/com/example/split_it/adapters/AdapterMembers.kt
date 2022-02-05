package com.example.split_it.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.database.model.User

class AdapterMembers(
    private val activityContext: Context,
    private val membersList: List<User>
) : RecyclerView.Adapter<AdapterMembers.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_members, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = membersList[position]
        
        // sets the text to the textview from our itemHolder class
        holder.profile_name.text = member.name
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return membersList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profile_name: TextView = itemView.findViewById(R.id.profile_name_view)
    }
}