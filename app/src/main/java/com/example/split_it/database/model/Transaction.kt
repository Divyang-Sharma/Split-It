package com.example.split_it.database.model

import androidx.room.PrimaryKey

data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val groupId: Int?,
    val expenseId: Int?,
    val fromUser: Int?,
    val toUser: Int?,

    var amount: Double?,
    var topicOfExpense: String? = ""
)
