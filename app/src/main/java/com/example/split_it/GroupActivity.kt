package com.example.split_it

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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

        val groupId = intent.getIntExtra("groupId",-1)
        val userId = intent.getIntExtra("userId",-1)


        if(userId == -1 || groupId == -1) {
            Toast.makeText(this,"Some error has occured!",LENGTH_SHORT).show()
            finish()
        }

        //UI assignments
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_2)

        // Setting adapter
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle, groupId,userId)
        viewPager2.adapter = viewPagerAdapter
        viewPagerAdapter.setUpTabTitles(viewPager2, tabLayout)


    }
}