package com.example.swmandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swmandroid.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }
        binding.findPasswordButton.setOnClickListener { finish() }
    }
}