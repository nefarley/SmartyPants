package com.example.smartypants.viewmodel

import androidx.lifecycle.*
import com.example.smartypants.database.Definitions
import com.example.smartypants.database.DefinitionsDao
import com.example.smartypants.database.DefinitionsRepository
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class DefinitionsViewModel(private val definitionsRepository: DefinitionsRepository): ViewModel(){
    val allDefinitions: LiveData<List<Definitions>> = definitionsRepository.allDefinitions.asLiveData()
    //val allDefinitions: LiveData<List<Definitions>> = definitionsRepository.allDefinitions.asLiveData()
    //val definition: LiveData<List<Definitions>> = definitionsRepository.definition.asLiveData()


    /**fun getDefinition(letter:String) = viewModelScope.launch {
        definitionsRepository.getDefinition(letter)
    }**/
    fun getDefinition(letter:String): LiveData<Definitions>{
        return definitionsRepository.definition(letter)
    }
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
    fun addNewDefinition(id: Int, letter: String, word: String, pronounce: String, definition: String){
        val newDefinition = getNewDefinition(id,letter,word,pronounce,definition)
        insertDefinition(newDefinition)
    }
    fun insertDefinition(definition: Definitions) = viewModelScope.launch {
        definitionsRepository.insert(definition)
    }

}