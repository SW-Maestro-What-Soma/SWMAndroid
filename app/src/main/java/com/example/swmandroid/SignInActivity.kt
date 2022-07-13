package com.example.swmandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swmandroid.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }
        binding.signInButton.setOnClickListener { finish() }
    }
}