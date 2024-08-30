package com.example.myapplication.core.repository

import com.example.myapplication.core.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepository {
    private val notes = buildList<Note> {
        repeat(100) {
            add(
                Note(id = it, title = "Title $it", content = "Content $it")
            )
        }
    }

    fun getNotes(): Flow<List<Note>> = flow {
        emit(notes)
    }

    fun getNote(noteId: Int): Flow<Note> = flow {
        emit(notes.first { it.id == noteId })
    }
}