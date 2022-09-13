package com.example.smartypants.viewmodel

import androidx.lifecycle.*
import com.example.smartypants.database.Definitions
import com.example.smartypants.database.DefinitionsDao
import com.example.smartypants.database.DefinitionsRepository
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class DefinitionsViewModel(private val definitionsRepository: DefinitionsRepository): ViewModel() {
    val allDefinitions: LiveData<List<Definitions>> = definitionsRepository.allDefinitions.asLiveData()
    //val definition: LiveData<List<Definitions>> = definitionsRepository.definition.asLiveData()

    /**
     * Launching a new coroutine to insert the data
     * in a non-blocking way
     */
    fun insertDefinition(definition: Definitions) = viewModelScope.launch {
        definitionsRepository.insert(definition)
    }
    class DefinitionViewModelFactory(private val definitionsRepository: DefinitionsRepository)
        : ViewModelProvider.Factory{
            override fun <T: ViewModel> create(modelClass: Class<T>): T{
                if(modelClass.isAssignableFrom(DefinitionsViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST")
                    return DefinitionsViewModel(definitionsRepository) as T
                }
                throw IllegalArgumentException("Unknown viewmodel class")
            }

        }
}