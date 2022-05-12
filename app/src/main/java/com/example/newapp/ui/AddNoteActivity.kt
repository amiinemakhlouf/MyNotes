package com.example.newapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.room.Room
import com.example.newapp.R
import com.example.newapp.data.NoteDatabase
import com.example.newapp.data.NoteDatabase_Impl
import com.example.newapp.data.entities.Note
import com.example.newapp.databinding.ActivityAddNoteBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.concurrent.thread

class AddNoteActivity : AppCompatActivity() {
    private  lateinit var  binding:ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val categorie=intent.getStringExtra("categorie")
        Log.d("test",categorie!!)
        val db =provideDB(this)

        binding.discard.setOnClickListener {

            finish()

        }
        binding.save.setOnClickListener {


            thread {


                db.noteDAO.upsertNote(Note("title",
                    binding.textField.editText!!.text.toString(),categorie

                ))
            }
            finish()



        }




        val categorieItems = listOf("personal", "work", "sport")
    }

    fun provideDB(@ApplicationContext context: Context): NoteDatabase {

        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}