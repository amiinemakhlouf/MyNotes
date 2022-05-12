package com.example.newapp.ui.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newapp.data.dao.NoteDao

class MainVMFactory (private val noteDao: NoteDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityVM::class.java)) {
            return MainActivityVM(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}