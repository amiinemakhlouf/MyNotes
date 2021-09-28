package com.example.newapp.data.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.newapp.data.dao.NoteDao
import com.example.newapp.data.entities.Note
import javax.inject.Inject


class  NoteRep @Inject constructor(val noteDao: NoteDao)  {
    suspend fun  upsertNote(note: Note)=noteDao.upsertNote(note)
    suspend  fun deleteNote(note:Note)=noteDao.deleteNote(note)
    fun getNotes()=noteDao.getNotes()


}