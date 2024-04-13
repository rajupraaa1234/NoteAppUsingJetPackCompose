package com.example.noteappusingjetpackcompose.notes_features.presentation.mvvm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappusingjetpackcompose.notes_features.data.Note
import com.example.noteappusingjetpackcompose.notes_features.data.NoteDao
import com.example.noteappusingjetpackcompose.notes_features.presentation.State.NoteState
import com.example.noteappusingjetpackcompose.notes_features.presentation.utils.NoteEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class NoteViewModel @Inject constructor(
    private val dao: NoteDao
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState>
        get() = _state

    private val isSortedByDateAdded = mutableStateOf(true)

    init {
        fetchData()
    }


    fun onEvent(noteEvent: NoteEvent) {
        when (noteEvent) {
            is NoteEvent.SaveNote -> {
                val note = Note(
                    title = _state.value.title.value,
                    content = _state.value.content.value,
                    dataAdded = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    dao.upsertNote(note = note)
                }
                _state.value = _state.value.copy(
                    title = mutableStateOf(""), content = mutableStateOf("")
                )
            }

            is NoteEvent.SortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
                fetchData()
            }

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(note = noteEvent.note)
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            var noteData = dao.getOrderedByDateAddedBy()
            if (!isSortedByDateAdded.value) {
                noteData = dao.getOrderedByTitle()
            }
            noteData.collect { notes ->
                _state.value = _state.value.copy(
                    notes = notes
                )
            }
        }
    }
}