package com.example.split_it.ui.activities

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.split_it.R
import com.example.split_it.ui.adapters.ViewPagerAdapter
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