package com.example.myapplication.core.data.repository

import com.example.myapplication.core.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    fun getNote(noteId: Int): Flow<Note>
    fun updateNote(noteId: Int, content: String)
}