package com.example.split_it.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.lifecycle.Lifecycle
import com.example.split_it.fragments.ExpensesFragment
import com.example.split_it.fragments.GroupMembersFragment
import com.example.split_it.fragments.TransactionsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle) :FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0->{
                GroupMembersFragment()
            }
            1->{
                ExpensesFragment()
            }
            2->{
                TransactionsFragment()
            }
           else -> {
               Fragment()
           }
       }
    }
}