package com.example.myapplication.feature.notedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel()
) {
    val note = viewModel.state.collectAsStateWithLifecycle()
    note.value?.let {
        Column(modifier = modifier) {
            Text("id is ${it.note.id}.")
            Text("title is ${it.note.title}.")
            Text("content is ${it.note.content}.")
        }
    }
}