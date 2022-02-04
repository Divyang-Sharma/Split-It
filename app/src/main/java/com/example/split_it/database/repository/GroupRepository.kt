package com.example.split_it.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.split_it.database.AppDatabase
import com.example.split_it.database.dao.GroupDao
import com.example.split_it.database.model.Group
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Middle layer between the UI and the database
 * Does the asynchronous operations.
 * In case of return value, returns livedata
 * Functions for `Group` table is defined in this class.
 */
class GroupRepository(val database: AppDatabase) {

    var dao: GroupDao = database.groupDao()

    /**
     * Inserts group to the table asynchronously.
     */
    fun insertGroup(group: Group) = CoroutineScope(Dispatchers.IO).launch {
        dao.insert(group)
    }

    /**
     * Updates group to the table asynchronously.
     */
    fun updateGroup(group: Group) = CoroutineScope(Dispatchers.IO).launch {
        dao.update(group)
    }
    /**
     * Deletes group to the table asynchronously.
     */
    fun deleteGroup(group: Group) = CoroutineScope(Dispatchers.IO).launch {
        dao.delete(group)
    }

    /**
     * Returns livedata of groups.
     */
    fun getGroups(): LiveData<List<Group>> {
        return dao.getGroups().asLiveData()
    }
}