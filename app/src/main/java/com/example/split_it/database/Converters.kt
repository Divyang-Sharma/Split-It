package com.example.split_it.database

import androidx.room.TypeConverter
import com.example.split_it.database.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * TypeConverter class for database.
 * Helps in storing complex objects as String.
 * Uses Gson library to convert to and from from String and Object
 */
class Converters {

    // List of Int
    @TypeConverter
    fun fromListOfInt(value: List<Int>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toListOfInt(value: String?): List<Int?>? {
        val listType= object : TypeToken<List<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

}