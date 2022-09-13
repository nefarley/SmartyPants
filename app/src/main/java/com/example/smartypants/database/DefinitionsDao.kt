package com.example.smartypants.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DefinitionsDao{
    @Query("SELECT * FROM definitions_table ORDER BY letter ASC")
    fun getAllDefinitions(): Flow<List<Definitions>>

    @Query("SELECT * FROM definitions_table WHERE letter = :letter")
    fun getDefinition(letter:String): Flow<List<Definitions>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(letter:Definitions)

    @Query("DELETE FROM definitions_table")
    suspend fun deleteAll()

}