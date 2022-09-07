package com.example.smartypants.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Declares the DefinitionsDao as a private property in the constructor.
 * Pass in the DAO instead of the whole database, because we only need
 * access to the DAO.
 **/
class DefinitionsRepository(private val definitionsDao: DefinitionsDao) {
    //Room will execute the queries in a separate thread
    val definitions: Flow<List<Definitions>> = definitionsDao.getDefinitions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(definition:Definitions){
        definitionsDao.insert(definition)
    }
}