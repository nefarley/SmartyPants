package com.example.smartypants

import android.app.Application
import com.example.smartypants.database.AppDatabase
import com.example.smartypants.database.DefinitionsRepository

class DefinitionsApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { DefinitionsRepository(database.definitionsDao()) }
}