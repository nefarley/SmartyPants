package com.example.smartypants.viewmodel

import androidx.lifecycle.*
import com.example.smartypants.database.Definitions
import com.example.smartypants.database.DefinitionsDao
import com.example.smartypants.database.DefinitionsRepository
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class DefinitionsViewModel(private val definitionsDao: DefinitionsDao): ViewModel(){
    val allDefinitions: LiveData<List<Definitions>> = definitionsDao.getAllDefinitions().asLiveData()

    /**fun getDefinition(letter:String) = viewModelScope.launch {
        definitionsRepository.getDefinition(letter)
    }**/
    /*fun getDefinition(letter:String): LiveData<Definitions>{
        //return definitionsRepository.definition(letter)
        return definitionsDao.getDefinition(letter)
    }*/
    /**
     * Launching a new coroutine to insert the data
     * in a non-blocking way
     */

   private fun getNewDefinition(iD: Int, letter: String, word: String, pronounce: String, definition: String): Definitions{
        return  Definitions(
            id = iD,
            letter = letter,
            word = word,
            pronounce = pronounce,
            definition = definition
        )
    }

    fun getDefinition(letter:String): LiveData<Definitions>{
        return definitionsDao.getDefinition(letter).asLiveData()
    }

    fun addNewDefinition(id: Int, letter: String, word: String, pronounce: String, definition: String){
        val newDefinition = getNewDefinition(id,letter,word,pronounce,definition)
        insertDefinition(newDefinition)
    }
    fun insertDefinition(definition: Definitions) = viewModelScope.launch {
        definitionsDao.insert(definition)
    }

}