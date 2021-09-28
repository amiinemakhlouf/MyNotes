package com.example.newapp.ui.display_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newapp.R
import dagger.hilt.EntryPoint

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

    }
}