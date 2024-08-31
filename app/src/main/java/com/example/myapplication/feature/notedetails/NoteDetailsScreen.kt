package com.example.myapplication.feature.notedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = koinViewModel()
) {
    val note = viewModel.state.collectAsStateWithLifecycle()
    note.value?.let {
        Column(modifier = modifier) {
            Text("id is ${it.note.id}.")
            Text("title is ${it.note.title}.")
            OutlinedTextField(
                value = it.note.content,
                onValueChange = viewModel::handleContentChanged,
                label = { Text("Content") })
        }
    }
}