package com.example.split_it.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.split_it.database.model.User

@Dao
interface UserDao {

    fun getUser() : LiveData<List<User>>
}