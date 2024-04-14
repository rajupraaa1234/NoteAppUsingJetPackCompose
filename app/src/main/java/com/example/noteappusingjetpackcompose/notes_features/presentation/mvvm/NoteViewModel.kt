package com.example.noteappusingjetpackcompose.notes_features.presentation.mvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note
import com.example.noteappusingjetpackcompose.notes_features.data.data_source.NoteDao
import com.example.noteappusingjetpackcompose.notes_features.domain.repository.NoteRepository
import com.example.noteappusingjetpackcompose.notes_features.presentation.State.NoteState
import com.example.noteappusingjetpackcompose.notes_features.presentation.utils.NoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
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
                    noteRepository.upsertNote(note = note)
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
                    noteRepository.deleteNote(note = noteEvent.note)
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            var noteData = noteRepository.getOrderedByDateAddedBy()
            if (!isSortedByDateAdded.value) {
                noteData = noteRepository.getOrderedByTitle()
            }
            noteData.collect { notes ->
                _state.value = _state.value.copy(
                    notes = notes
                )
            }
        }
    }
}