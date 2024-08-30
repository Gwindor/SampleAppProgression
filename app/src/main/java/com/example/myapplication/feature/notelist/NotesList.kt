package com.example.myapplication.feature.notelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    onNavigateDetails: (Int) -> Unit
) {
    Column(modifier = Modifier) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                100,
            ) {
                Row(modifier = Modifier.clickable {
                    onNavigateDetails(it)
                }) {
                    Text(
                        text = "Hello $it!",
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
            onNavigateDetails = {}
        )
    }
}