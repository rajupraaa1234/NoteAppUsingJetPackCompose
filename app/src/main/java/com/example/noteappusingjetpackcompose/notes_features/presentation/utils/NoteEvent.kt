package com.example.noteappusingjetpackcompose.notes_features.presentation.utils

import android.icu.text.CaseMap.Title
import com.example.noteappusingjetpackcompose.notes_features.data.Note

sealed interface NoteEvent {
    object SortNotes : NoteEvent
    data class DeleteNote(var note: Note) : NoteEvent
    data class SaveNote(var title: String, var content: String) : NoteEvent

}