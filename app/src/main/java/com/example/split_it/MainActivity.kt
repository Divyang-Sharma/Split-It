package com.example.split_it

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.split_it.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database by lazy { AppDatabase.getDatabase(this) }

        val dao = database.userDao()

//        dao.getUser().observe(this, Observer {  })

    }
}