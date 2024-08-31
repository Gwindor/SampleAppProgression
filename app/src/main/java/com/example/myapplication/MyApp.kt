package com.example.myapplication

import android.app.Application
import com.example.myapplication.core.data.repository.NoteRepository
import com.example.myapplication.core.data.repository.NoteRepositoryImpl
import com.example.myapplication.feature.notedetails.NoteDetailsViewModel
import com.example.myapplication.feature.notelist.NotesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                module {
                    single<NoteRepository> { NoteRepositoryImpl() }
                    viewModel { NotesViewModel(get()) }
                    viewModel { NoteDetailsViewModel(get(), get()) }
                }
            )
        }
    }
}