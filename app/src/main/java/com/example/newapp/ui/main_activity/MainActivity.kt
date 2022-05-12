package com.example.newapp.ui.main_activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.newapp.DisplayFragment
import com.example.newapp.R
import com.example.newapp.data.NoteDatabase
import com.example.newapp.data.dao.NoteDao_Impl
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.ui.AddNoteActivity
import com.example.newapp.ui.FavoritesFragment
import com.example.newapp.utils.NoteAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import dagger.hilt.android.qualifiers.ApplicationContext

class MainActivity : AppCompatActivity(){
    private lateinit var viewModel: MainActivityVM
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    // private  val viewModel:SplashActivityVM by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            mOnNavigationItemSelectedListener()






        binding.bottomAppBar.setOnItemSelectedListener {



            when(it.itemId){

                R.id.favorites2 -> {

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container_view, FavoritesFragment()).commit()

                    }
                    true
                }

                R.id.myMenu -> {

                    Toast.makeText(this,"menu",Toast.LENGTH_LONG).show()

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container_view, DisplayFragment()).commit()

                    }
                    true

                }
                else-> false






                }
            }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, DisplayFragment()).commit()
        }
        }




    private fun mOnNavigationItemSelectedListener() =
        binding.navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.favorites -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container_view, FavoritesFragment()).commit()

                    }
                    return@setNavigationItemSelectedListener true
                }
                R.id.myMenu -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container_view, DisplayFragment()).commit()

                    }
                    return@setNavigationItemSelectedListener true

                }
                else -> false
            }


        }
}
