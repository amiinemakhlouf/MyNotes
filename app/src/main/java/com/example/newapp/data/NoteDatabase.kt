package com.example.newapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newapp.Constants
import com.example.newapp.data.dao.NoteDao
import com.example.newapp.data.entities.Note

@Database(entities = [Note::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {

     abstract  val noteDAO:NoteDao
     companion object{
         val DATABASE_NAME=Constants.DATABASE_NAME
     }

    }


