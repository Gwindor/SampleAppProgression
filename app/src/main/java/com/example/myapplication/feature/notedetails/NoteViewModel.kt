package com.example.myapplication.feature.notedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class NoteViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    val test = savedStateHandle.get<Int>("id")

}