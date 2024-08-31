package com.example.myapplication.feature.notedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.data.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NoteDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository
) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle["id"])

    val state = repository.getNote(noteId)
        .map { NoteDetailsUiState.Success(note = it) }
        .catch { NoteDetailsUiState.Error(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NoteDetailsUiState.Loading
        )

    fun handleContentChanged(content: String) {
        repository.updateNote(noteId, content)
    }
}

