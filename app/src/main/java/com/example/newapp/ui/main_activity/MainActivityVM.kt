package com.example.newapp.ui.main_activity

import androidx.lifecycle.ViewModel
import com.example.newapp.data.dao.NoteDao
import com.example.newapp.data.entities.Note
import kotlin.concurrent.thread

class MainActivityVM(
    val noteDao:NoteDao
) : ViewModel (){

    fun getAllNotes()= noteDao.getNotes()
    fun deleteItem(note: Note) = thread { noteDao.deleteNote(note) }
    fun updateNote(note:Note) = thread { noteDao.upsertNote(note) }
}