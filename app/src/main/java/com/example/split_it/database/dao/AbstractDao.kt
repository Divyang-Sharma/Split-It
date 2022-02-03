package com.example.split_it.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.split_it.database.model.User

interface AbstractDao<T> {

    /**
     * Inserts a single entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T)

    /**
     * Inserts a list of entities into the table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(t: List<T>)

    /**
     * Updates the given entity in the table
     */
    @Update
    suspend fun update(t: T)

    /**
     * Deletes the given entity from the table
     */
    @Delete
    suspend fun delete(t: T)
}