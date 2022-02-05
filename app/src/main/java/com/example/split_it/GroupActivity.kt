package com.example.split_it

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.split_it.adapters.ViewPagerAdapter
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.model.Expense
import com.example.split_it.database.model.Group
import com.example.split_it.database.model.User
import com.example.split_it.database.repository.ExpenseRepository
import com.example.split_it.database.repository.GroupRepository
import com.example.split_it.database.repository.UserRepository
import com.google.android.material.tabs.TabLayout

class GroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        //UI assignments
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_2)

        // Setting adapter
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = viewPagerAdapter
        viewPagerAdapter.setUpTabTitles(viewPager2, tabLayout)


        //Testing Purpose
        val database = AppDatabase.getDatabase(this)
        val userRepository = UserRepository(database)
        val groupRepository = GroupRepository(database)
        val expenseRepository = ExpenseRepository(database)

        val user1 = User(1,"split","splitupi")
        val user2 = User(1,"split","splitupi")

        val groups = ArrayList<Int>()
        groups.add(1)
        val group = Group(1, "splitgroup",groups)
        val expense = Expense(1,1,1,200.0,"splitopic")

        userRepository.insertUser(user1)
        userRepository.insertUser(user2)

        groupRepository.insertGroup(group)
        expenseRepository.insertExpense(expense)




    }
}