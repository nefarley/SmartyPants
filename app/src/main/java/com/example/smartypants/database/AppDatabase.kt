package com.example.smartypants.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Definitions::class], version = 1, exportSchema = false)
public abstract class AppDatabase: RoomDatabase() {
    abstract fun definitionsDao(): DefinitionsDao

    companion object{
        /**
         * Singleton that provides multiple instances of a
         * database opening at the same time.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            //if the instance is not null, return database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                "definitions_database").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}