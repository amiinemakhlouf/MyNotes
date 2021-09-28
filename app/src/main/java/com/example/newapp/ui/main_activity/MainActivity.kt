package com.example.newapp.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.newapp.R
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.databinding.ActivitySplashBinding
import com.example.newapp.ui.splashActivity.SplashActivityVM
import dagger.hilt.EntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   // private  val viewModel:SplashActivityVM by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }}
