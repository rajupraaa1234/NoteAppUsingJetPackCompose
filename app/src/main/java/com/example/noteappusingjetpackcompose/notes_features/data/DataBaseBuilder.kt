package com.example.noteappusingjetpackcompose.notes_features.data

import android.content.Context
import androidx.room.Room

class DataBaseBuilder {
    companion object {
        operator fun invoke(applicationContext: Context): NoteDataBase {
            return Room.databaseBuilder(
                applicationContext,
                NoteDataBase::class.java,
                name = NoteDataBase.DATABASE_NAME
            ).build()
        }

    }
}