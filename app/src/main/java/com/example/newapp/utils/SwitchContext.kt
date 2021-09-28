package com.example.newapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

class SwitchContext() {
      fun invoke(context1: Context,context2: Context)
    {
        val intent= Intent(context1,context2::class.java)
        context1.startActivity(intent)

    }
}