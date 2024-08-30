package com.example.myapplication

import android.app.Application
import com.example.myapplication.core.repository.NoteRepository

class MyApp : Application() {
    lateinit var repo: NoteRepository
        private set

    override fun onCreate() {
        super.onCreate()
        repo = NoteRepository()
    }
}