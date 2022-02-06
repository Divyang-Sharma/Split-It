package com.example.split_it.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.split_it.common.TabLayoutConstants.EXPENSES_TAB_NAME
import com.example.split_it.common.TabLayoutConstants.EXPENSE_TAB_POSITION
import com.example.split_it.common.TabLayoutConstants.GROUP_MEMBERS_TAB_NAME
import com.example.split_it.common.TabLayoutConstants.GROUP_MEMBERS_TAB_POSITION
import com.example.split_it.common.TabLayoutConstants.TRANSACTIONS_TAB_NAME
import com.example.split_it.common.TabLayoutConstants.TRANSACTIONS_TAB_POSITION
import com.example.split_it.fragments.ExpensesFragment
import com.example.split_it.fragments.GroupMembersFragment
import com.example.split_it.fragments.TransactionsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Adapter for the viewpager which is responsible for the tabs
 * to be displayed.
 */
class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val groupId: Int,
    val userId: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    // order in which tabs are displayed
    private val tabNames = arrayOf(
        GROUP_MEMBERS_TAB_NAME,
        EXPENSES_TAB_NAME,
        TRANSACTIONS_TAB_NAME
    )

    /**
     * The total number of tabs.
     * Its equal to the size of the `tabNames` array
     */
    override fun getItemCount(): Int {
        return tabNames.size
    }

    /**
     * Instantiates the fragment class based on the position.
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            GROUP_MEMBERS_TAB_POSITION -> GroupMembersFragment(groupId)
            EXPENSE_TAB_POSITION -> ExpensesFragment(groupId,userId)
            TRANSACTIONS_TAB_POSITION -> TransactionsFragment(groupId,userId)
            else -> Fragment() // default case | shouldn't happen
        }
    }

    /**
     * The titles of the tab is displayed in here.
     * It uses TabLayoutMediator to display the titles from the array.
     * Should be used only after assigning the adapter to the viewPager
     */
    fun setUpTabTitles(
        viewPager: ViewPager2,
        tabLayout: TabLayout
    ) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}