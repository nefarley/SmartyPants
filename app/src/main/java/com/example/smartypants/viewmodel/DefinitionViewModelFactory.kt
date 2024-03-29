package com.example.smartypants.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartypants.database.DefinitionsDao
import com.example.smartypants.database.DefinitionsRepository

class DefinitionViewModelFactory(private val definitionsDao: DefinitionsDao): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(DefinitionsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DefinitionsViewModel(definitionsDao) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}