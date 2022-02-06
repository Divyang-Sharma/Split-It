package com.example.split_it.database

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * TypeConverter class for database.
 * Helps in storing complex objects as String.
 * Uses Gson library to convert to and from from String and Object
 */
class Converters {

    // List of Int
    @TypeConverter
    fun fromListOfInt(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun toListOfInt(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

}