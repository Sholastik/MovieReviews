package com.vyacheslavivanov.moviereviews

import android.app.Activity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vyacheslavivanov.moviereviews.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Should be run before super
        installSplashScreen() // SplashScreen won't show when run via Android Studio: https://issuetracker.google.com/issues/205021357

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}