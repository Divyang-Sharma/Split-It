package com.example.split_it.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.split_it.R
import com.example.split_it.viewModels.ItemViewModelMembers

class AdapterMembers(private val mList: List<ItemViewModelMembers>) : RecyclerView.Adapter<AdapterMembers.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_members, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.profile_image.setImageResource(ItemsViewModel.profile_image)

        // sets the text to the textview from our itemHolder class
        holder.profile_name.text = ItemsViewModel.profile_name
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profile_image: ImageView = itemView.findViewById(R.id.profile_image_view)
        val profile_name: TextView = itemView.findViewById(R.id.profile_name_view)
    }
}