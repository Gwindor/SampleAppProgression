package com.example.myapplication.feature.notelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.core.repository.NoteRepository
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = koinViewModel(),
    onNavigateDetails: (Int) -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(modifier = Modifier) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                state.value,
                key = {
                    it.id
                }
            ) {
                Row(modifier = Modifier.clickable {
                    onNavigateDetails(it.id)
                }) {
                    Text(
                        text = "Hello ${it.title}!",
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        NoteListScreen(
            modifier = Modifier,
            viewModel = NotesViewModel(
                repository = NoteRepository()
            ),
            onNavigateDetails = {}
        )
    }
}