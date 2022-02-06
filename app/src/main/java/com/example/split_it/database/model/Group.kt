package com.example.split_it.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Group (

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var name: String?,

    //foreign keys
    var users: List<Int>?,
)
