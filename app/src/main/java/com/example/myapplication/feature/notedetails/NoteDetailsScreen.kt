package com.example.myapplication.feature.notedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteDetailsScreen(viewModel: NoteDetailsViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NoteDetailsScreen(
        modifier = Modifier,
        state = state,
        onContentChanged = viewModel::handleContentChanged
    )
}

@Composable
private fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    state: NoteDetailsUiState,
    onContentChanged: (String) -> Unit
) {
    when (state) {
        is NoteDetailsUiState.Error -> {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                text = "Error: ${state.throwable.message.toString()}"
            )
        }

        NoteDetailsUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is NoteDetailsUiState.Success -> {
            Column(modifier = modifier) {
                Text("id is ${state.note.id}.")
                Text("title is ${state.note.title}.")
                OutlinedTextField(
                    value = state.note.content,
                    onValueChange = onContentChanged,
                    label = { Text("Content") })
            }
        }
    }
}