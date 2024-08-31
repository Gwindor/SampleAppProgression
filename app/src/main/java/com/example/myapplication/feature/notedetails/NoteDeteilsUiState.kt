package com.example.myapplication.feature.notedetails

import com.example.myapplication.core.model.Note

sealed interface NoteDetailsUiState {
    data class Success(val note: Note):NoteDetailsUiState
    data object Loading : NoteDetailsUiState
    data class Error(val throwable: Throwable) : NoteDetailsUiState
}

