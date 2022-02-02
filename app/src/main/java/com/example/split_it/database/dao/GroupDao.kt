package com.example.split_it.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.split_it.database.model.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao : AbstractDao<Group> {

    /**
     * Returns all the groups from the table
     */
    @Query("SELECT * FROM `Group`")
    fun getGroups(): Flow<List<Group>>

}