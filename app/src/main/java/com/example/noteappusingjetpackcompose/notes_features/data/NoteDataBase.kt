package com.example.noteappusingjetpackcompose.notes_features.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDataBase : RoomDatabase(){
    abstract val noteDao : NoteDao

    companion object{
        const val DATABASE_NAME = "note_db"
    }
}