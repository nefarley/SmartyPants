package com.example.smartypants.viewmodel

import androidx.lifecycle.*
import com.example.smartypants.database.Definitions
import com.example.smartypants.database.DefinitionsRepository
import kotlinx.coroutines.launch

class DefinitionsViewModel(private val definitionsRepository: DefinitionsRepository): ViewModel() {
    val definitions: LiveData<List<Definitions>> = definitionsRepository.definitions.asLiveData()

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