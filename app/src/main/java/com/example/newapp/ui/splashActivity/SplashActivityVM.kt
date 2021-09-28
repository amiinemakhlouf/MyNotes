package com.example.newapp.ui.splashActivity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.newapp.Constants
import com.example.newapp.databinding.ActivitySplashBinding
import com.example.newapp.ui.main_activity.MainActivity
import com.example.newapp.utils.SwitchContext
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashActivityVM @Inject constructor()  :ViewModel() {

    fun hideSystemUI(binding: ViewBinding, context: Activity) {
        WindowCompat.setDecorFitsSystemWindows(context.window, false)
        WindowInsetsControllerCompat(context.window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    fun automaticPAss(context: Activity, binding: ActivitySplashBinding) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        hideSystemUI(binding, context)
        Handler(Looper.getMainLooper()).postDelayed({

             SwitchContext().invoke(context,MainActivity())


        }, Constants.DELAY_MILLIS)

    }

}