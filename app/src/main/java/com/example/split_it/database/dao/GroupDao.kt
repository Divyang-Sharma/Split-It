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

    /**
     * Returns all the groups from the table
     */
    @Query("SELECT * FROM `Group` WHERE id=:groupId")
    fun getGroupFromId(groupId: Int): Flow<Group>

    /**
     * Returns all the groups from the table
     */
    @Query("SELECT * FROM `Group` WHERE :userId IN (users)")
    fun getGroupForUser(userId: Int): Flow<List<Group>>

    /**
     * Returns all the groups from the table
     */
    @Query("SELECT * FROM `Group` WHERE id=:groupId")
    fun getGroupFromIdInSync(groupId: Int): Group



}