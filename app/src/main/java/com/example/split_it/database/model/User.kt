package com.example.split_it.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var name: String?,
    var upi: String?,

)