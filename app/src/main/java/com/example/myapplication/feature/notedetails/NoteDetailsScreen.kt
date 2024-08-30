package com.example.myapplication.feature.notedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel()
) {
    val id = viewModel.test
    Column(modifier = modifier) {
        Text("id is $id")
    }
}