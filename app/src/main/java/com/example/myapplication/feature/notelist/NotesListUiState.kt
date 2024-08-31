package com.example.myapplication.feature.notelist

import com.example.myapplication.core.model.Note

sealed interface NotesListUiState {

    data object Loading : NotesListUiState

    data class Success(
        val data: List<Note>
    ) : NotesListUiState

    data class Error(val throwable: Throwable) : NotesListUiState
}


