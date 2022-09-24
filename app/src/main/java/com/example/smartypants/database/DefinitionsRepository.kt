package com.example.smartypants.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.smartypants.LetterAdapter
import kotlinx.coroutines.flow.Flow

/**
 * Declares the DefinitionsDao as a private property in the constructor.
 * Pass in the DAO instead of the whole database, because we only need
 * access to the DAO.
 **/
class DefinitionsRepository(private val definitionsDao: DefinitionsDao) {
    //Room will execute the queries in a separate thread
    val allDefinitions: Flow<List<Definitions>> = definitionsDao.getAllDefinitions()
    //val definition: Flow<List<Definitions>> = definitionsDao.getDefinition()

     fun definition(letter:String): LiveData<Definitions>{
        return  definitionsDao.getDefinition(letter).asLiveData()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(definition:Definitions){
        definitionsDao.insert(definition)
    }

}