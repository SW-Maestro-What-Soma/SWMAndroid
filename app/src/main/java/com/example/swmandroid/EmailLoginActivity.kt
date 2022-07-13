package com.example.swmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swmandroid.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEmailLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish()  }
        binding.loginButton.setOnClickListener {
            moveActivity(SetTechActivity())
        }
        binding.signInTextview.setOnClickListener {
            moveActivity(SignInActivity())
        }
        binding.findPasswordTextview.setOnClickListener {
            moveActivity(FindPasswordActivity())
        }
    }

    private fun moveActivity(goToActivity : AppCompatActivity){
        val intent = Intent(this, goToActivity::class.java)
        startActivity(intent)
    }
}