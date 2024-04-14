package com.example.noteappusingjetpackcompose.notes_features.presentation.State

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note


data class NoteState(
    var notes: List<Note> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val content: MutableState<String> = mutableStateOf("")
)