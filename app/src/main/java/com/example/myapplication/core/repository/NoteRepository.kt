package com.example.myapplication.core.repository

import android.util.Log
import com.example.myapplication.core.model.Note
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class NoteRepository {

    private val notesFlow: MutableSharedFlow<List<Note>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        notesFlow.tryEmit(buildList {
            repeat(100) {
                add(
                    Note(id = it, title = "Title $it", content = "Content $it")
                )
            }
        })
    }

    fun getNotes(): Flow<List<Note>> = notesFlow

    fun getNote(noteId: Int): Flow<Note> = notesFlow.map { notes ->
        notes.first {
            it.id == noteId
        }
    }

    fun updateNote(noteId: Int, content: String) {
        (notesFlow.replayCache.firstOrNull() ?: emptyList()).let { notes ->
            val index = notes.indexOfFirst { it.id == noteId }
            val mutableList = notes.toMutableList()
            mutableList[index] = mutableList[index].copy(content = content)
            notesFlow.tryEmit(mutableList.toList())
        }
    }
}