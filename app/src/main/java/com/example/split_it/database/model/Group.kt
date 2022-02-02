package com.example.split_it.database.model

import androidx.room.PrimaryKey
import java.util.*

data class Group (

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var name: String?,

    //foreign keys
    var users: List<Int>? = ArrayList<Int>(),
)
