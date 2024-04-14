package com.example.noteappusingjetpackcompose.notes_features.domain.repository

import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getOrderedByDateAddedBy(): Flow<List<Note>>

    fun getNotes(): Flow<List<Note>>

    fun getOrderedByTitle(): Flow<List<Note>>
}