package com.example.swmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.swmandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fullServiceButton.setOnClickListener { moveActivity(FullServiceActivity()) }

        //Todo 닉네임 로직 구현시 변경
        binding.nickNameTextview.text = "김시진님"

        binding.myPageButton.setOnClickListener { moveActivity(MyPageActivity()) }
        binding.easyLearningButton.setOnClickListener { moveActivity(EasyLearningActivity()) }
        binding.testButton.setOnClickListener { moveActivity(TestActivity()) }
        binding.communityButton.setOnClickListener { moveActivity(CommunityActivity()) }
    }

    private fun moveActivity(goToActivity: AppCompatActivity) {
        val intent = Intent(this, goToActivity::class.java)
        startActivity(intent)
    }
}