package com.example.newapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.newapp.R
import com.example.newapp.data.NoteDatabase
import com.example.newapp.data.entities.Note
import com.example.newapp.databinding.FragmentFavoritesBinding
import com.example.newapp.ui.main_activity.MainActivityVM
import com.example.newapp.ui.main_activity.MainVMFactory
import dagger.hilt.android.qualifiers.ApplicationContext


class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(),"iam favorites",Toast.LENGTH_LONG).show()
        val db = provideDB(requireContext())
        var listOfFac= mutableListOf<String>()
        var adapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,listOfFac)
        binding.lv.adapter=adapter
        val mainVMFactory = MainVMFactory(db.noteDAO)
        val viewModel = ViewModelProvider(this, mainVMFactory).get(MainActivityVM::class.java)
         viewModel.getFavoritesNote().observe(requireActivity()){

             it?.let {
                 Log.d("myTAGFavorites",it.toString())
                 val jusContent= it.map { it.content }
                  adapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,jusContent)
                 binding.lv.adapter=adapter


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
}