package com.example.split_it

import android.app.Application
import com.example.split_it.database.AppDatabase

class SplitItApp : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
}