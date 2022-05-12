package com.example.newapp.ui.main_activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.newapp.R
import com.example.newapp.data.NoteDatabase
import com.example.newapp.data.dao.NoteDao_Impl
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.ui.AddNoteActivity
import com.example.newapp.utils.NoteAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import dagger.hilt.android.qualifiers.ApplicationContext

class MainActivity : AppCompatActivity(),
    NoteAdapter.ClickListener {
    private lateinit var viewModel: MainActivityVM
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    // private  val viewModel:SplashActivityVM by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var isFabButtonMenuVisible = false
        setContentView(binding.root)

        val db = provideDB(this)
        val mainVMFactory = MainVMFactory(db.noteDAO)
        viewModel = ViewModelProvider(this, mainVMFactory).get(MainActivityVM::class.java)
        noteAdapter = NoteAdapter(this, mutableListOf(), this)






        binding.fab.setOnClickListener {

            when (isFabButtonMenuVisible) {

                false -> {
                    binding.fabWork.isVisible = true
                    binding.fabPersonal.isVisible = true
                    binding.fabSport.isVisible = true
                    isFabButtonMenuVisible = true
                }

                true -> {
                    binding.fabWork.isVisible = false
                    binding.fabPersonal.isVisible = false
                    binding.fabSport.isVisible = false
                    isFabButtonMenuVisible = false

                }

            }
        }

        binding.fabSport.setOnClickListener {

            Intent(this, AddNoteActivity::class.java).putExtra("categorie", "sport")
                .also {
                    startActivity(it)
                }
        }
        binding.fabPersonal.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).putExtra("categorie", "personal").also {

                startActivity(it)
            }
        }
        binding.fabWork.setOnClickListener {

            Intent(this, AddNoteActivity::class.java).putExtra("categorie", "work").also {
                startActivity(it)
            }
        }


    }

    fun provideDB(@ApplicationContext context: Context): NoteDatabase {

        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = binding.rv
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.getAllNotes().observe(this) {
            val listOfNotes = it
            Log.d("firstTest", listOfNotes.toString())
            Log.d("secondTest", listOfNotes.toString())
            it?.let {
                noteAdapter = NoteAdapter(this, it.toMutableList(), this)

            }

            recyclerView.adapter = noteAdapter
            noteAdapter.notifyDataSetChanged()

        }
    }

    override fun onItemDeleted(position: Int) {
        viewModel.deleteItem(noteAdapter.dataSEt[position])
        //noteAdapter.notifyDataSetChanged()
    }

    override fun onAddItemToFavorites(position: Int) {
        val noteToAddToFavorite= noteAdapter.dataSEt[position]
        noteToAddToFavorite.isFavorite=true
        viewModel.updateNote(noteToAddToFavorite)
    }


}
