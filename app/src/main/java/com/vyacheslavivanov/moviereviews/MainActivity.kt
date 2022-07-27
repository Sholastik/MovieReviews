package com.vyacheslavivanov.moviereviews

import android.app.Activity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // SplashScreen won't show when run via Android Studio: https://issuetracker.google.com/issues/205021357

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}