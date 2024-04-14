package com.example.noteappusingjetpackcompose.notes_features.data.repository
import com.example.noteappusingjetpackcompose.notes_features.data.data_source.NoteDao
import com.example.noteappusingjetpackcompose.notes_features.domain.model.Note
import com.example.noteappusingjetpackcompose.notes_features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) : NoteRepository {
    override suspend fun upsertNote(note: Note) {
        dao.upsertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override fun getOrderedByDateAddedBy(): Flow<List<Note>> {
        return dao.getOrderedByDateAddedBy()
    }

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override fun getOrderedByTitle(): Flow<List<Note>> {
        return dao.getOrderedByTitle()
    }
}