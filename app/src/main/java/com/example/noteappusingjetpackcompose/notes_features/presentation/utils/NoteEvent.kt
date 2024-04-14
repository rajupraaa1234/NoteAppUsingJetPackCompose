package com.example.noteappusingjetpackcompose.notes_features.presentation.utils

import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note

sealed interface NoteEvent {
    object SortNotes : NoteEvent
    data class DeleteNote(var note: Note) : NoteEvent
    data class SaveNote(var title: String, var content: String) : NoteEvent

}