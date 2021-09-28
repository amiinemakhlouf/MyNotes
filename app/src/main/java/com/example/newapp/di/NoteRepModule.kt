package com.example.newapp.di

import com.example.newapp.data.dao.NoteDao
import com.example.newapp.data.repo.NoteRep
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteRepModule () {

    @Singleton
    @Provides
    fun providesNoteRep(noteDao:NoteDao):NoteRep{
        return NoteRep(noteDao)
    }
}