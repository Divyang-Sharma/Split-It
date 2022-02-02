package com.example.split_it.database.model

import androidx.room.PrimaryKey

data class Expense(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    //foreign keys
    val groupId: Int?,
    val paidByUser: Int?,

    var amount: Double?,
    var topicOfExpense: String?

)
