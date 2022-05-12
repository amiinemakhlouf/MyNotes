package com.example.newapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.newapp.data.NoteDatabase
import com.example.newapp.databinding.FragmentDisplayBinding
import com.example.newapp.ui.AddNoteActivity
import com.example.newapp.ui.main_activity.MainActivityVM
import com.example.newapp.ui.main_activity.MainVMFactory
import com.example.newapp.utils.NoteAdapter
import dagger.hilt.android.qualifiers.ApplicationContext


class DisplayFragment : Fragment(),NoteAdapter.ClickListener {
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: MainActivityVM
    private  lateinit var  binding:FragmentDisplayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
         binding= FragmentDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

     override  fun  onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val recyclerView = binding.rv
         noteAdapter = NoteAdapter(requireActivity(), mutableListOf(),this)
         recyclerView.adapter = noteAdapter
         recyclerView.layoutManager = LinearLayoutManager(activity)


         var isFabButtonMenuVisible = false
         val db=provideDB(requireContext())
         val mainVMFactory = MainVMFactory(db.noteDAO)
         viewModel = ViewModelProvider(this, mainVMFactory).get(MainActivityVM::class.java)
         noteAdapter = NoteAdapter(requireContext(), mutableListOf(), this)

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

             Intent(activity, AddNoteActivity::class.java).putExtra("categorie", "sport")
                 .also {
                     startActivity(it)
                 }
         }
         binding.fabPersonal.setOnClickListener {
             Intent(activity, AddNoteActivity::class.java).putExtra("categorie", "personal").also {

                 startActivity(it)
             }
         }
         binding.fabWork.setOnClickListener {

             Intent(activity, AddNoteActivity::class.java).putExtra("categorie", "work").also {
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

    override fun onItemDeleted(position: Int) {
        viewModel.deleteItem(noteAdapter.dataSEt[position])
        noteAdapter.notifyItemRemoved(position)
    }

    override fun onAddItemToFavorites(position: Int) {
        noteAdapter.dataSEt[position].isFavorite=1
        Log.d("myTAG",""+noteAdapter.dataSEt[position].isFavorite)
        viewModel.updateNote(noteAdapter.dataSEt[position])
        noteAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            val listOfNotes = it
            Log.d("firstTest", listOfNotes.toString())
            Log.d("secondTest", listOfNotes.toString())
            it?.let {
                noteAdapter = NoteAdapter(requireContext(), it, this)

            }
            if(it==null){
                noteAdapter = NoteAdapter(requireContext(), mutableListOf(), this)

            }

            binding.rv.adapter = noteAdapter
            noteAdapter.notifyDataSetChanged()

        }
    }


}