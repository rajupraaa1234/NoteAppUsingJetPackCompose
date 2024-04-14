package com.example.noteappusingjetpackcompose.notes_features.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getOrderedByTitle(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY dataAdded")
    fun getOrderedByDateAddedBy(): Flow<List<Note>>

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>


}