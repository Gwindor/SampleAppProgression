package com.example.myapplication.feature.notedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NoteDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository
) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle["id"])

    val state = repository.getNote(noteId)
        .map { NoteDetailsUiState(note = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun handleContentChanged(content: String) {
        repository.updateNote(noteId, content)
    }
}

