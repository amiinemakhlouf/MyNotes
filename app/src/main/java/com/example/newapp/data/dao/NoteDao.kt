package com.example.newapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newapp.data.entities.Note
import java.util.concurrent.Flow

@Dao
interface  NoteDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun  upsertNote(note:Note)
    @Delete
      fun deleteNote(note:Note)
     @Query("select * from note")
     fun getNotes():LiveData<List<Note>?>
    @Update

    fun updateUsers(vararg users: Note)

}