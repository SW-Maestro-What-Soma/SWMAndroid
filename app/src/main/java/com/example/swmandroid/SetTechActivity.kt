package com.example.swmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swmandroid.databinding.ActivitySetTechBinding

class SetTechActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetTechBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetTechBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }
        binding.techFrontendButton.setOnClickListener { moveSetNickActivity() }
        binding.techBackendButton.setOnClickListener { moveSetNickActivity() }
        binding.techAndroidButton.setOnClickListener { moveSetNickActivity() }
        binding.techIosButton.setOnClickListener { moveSetNickActivity() }
        binding.techDataButton.setOnClickListener { moveSetNickActivity() }
    }

    private fun moveSetNickActivity() {
        val intent = Intent(this, SetNickActivity::class.java)
        startActivity(intent)
    }
}