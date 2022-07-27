package com.vyacheslavivanov.moviereviews

import android.app.Activity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vyacheslavivanov.moviereviews.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Should be run before super
        // SplashScreen won't show when run via Android Studio: https://issuetracker.google.com/issues/205021357
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}