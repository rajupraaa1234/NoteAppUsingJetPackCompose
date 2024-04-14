package com.example.noteappusingjetpackcompose.notes_features.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note

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