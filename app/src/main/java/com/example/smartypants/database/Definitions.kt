package com.example.smartypants.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definitions_table")
class Definitions(@NonNull @ColumnInfo
                    (name = "id") val id: Int,
                @PrimaryKey @NonNull @ColumnInfo
                    (name ="letter") val letter: String,
                @NonNull @ColumnInfo
                    (name = "word") val word: String,
                @NonNull @ColumnInfo
                    (name = "pronounce") val pronounce: String,
                @NonNull @ColumnInfo
                    (name = "definition") val definition: String
                )