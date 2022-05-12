package com.example.newapp

import android.app.Application

class myApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
    companion object{
         lateinit var  instance:myApplication
    }
}