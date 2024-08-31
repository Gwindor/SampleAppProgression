package com.example.myapplication.feature.notelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.core.model.Note
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteListScreen(
    viewModel: NotesViewModel = koinViewModel(),
    onNavigateDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NoteListScreen(
        modifier = Modifier,
        onNavigateDetails = onNavigateDetails,
        state = state
    )
}

@Composable
private fun NoteListScreen(
    state: NotesListUiState,
    modifier: Modifier = Modifier,
    onNavigateDetails: (Int) -> Unit
) {
    when (state) {
        is NotesListUiState.Error -> {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                text = "Error: ${state.throwable.message.toString()}"
            )
        }

        NotesListUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is NotesListUiState.Success -> {
            Column(modifier = modifier) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        state.data,
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
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewLoading() {
    MyApplicationTheme {
        NoteListScreen(
            modifier = Modifier,
            onNavigateDetails = {},
            state = NotesListUiState.Loading
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewError() {
    MyApplicationTheme {
        NoteListScreen(
            modifier = Modifier,
            onNavigateDetails = {},
            state = NotesListUiState.Error(IllegalStateException("Test"))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSuccessEmpty() {
    MyApplicationTheme {
        NoteListScreen(
            modifier = Modifier,
            onNavigateDetails = {},
            state = NotesListUiState.Success(emptyList())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSuccess5Items() {
    MyApplicationTheme {
        NoteListScreen(
            modifier = Modifier,
            onNavigateDetails = {},
            state = NotesListUiState.Success(buildList {
                add(Note(1, "Title 1", "Content 1"))
                add(Note(2, "Title 2", "Content 2"))
                add(Note(3, "Title 3", "Content 3"))
                add(Note(4, "Title 4", "Content 4"))
                add(Note(5, "Title 5", "Content 5"))
            })
        )
    }
}