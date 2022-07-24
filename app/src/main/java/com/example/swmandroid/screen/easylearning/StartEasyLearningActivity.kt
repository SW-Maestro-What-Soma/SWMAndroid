package com.example.swmandroid.screen.easylearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swmandroid.databinding.ActivityStartEasyLearningBinding
import com.example.swmandroid.screen.easylearning.adapter.LearningProblemViewPagerAdapter

class StartEasyLearningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartEasyLearningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartEasyLearningBinding.inflate(layoutInflater)

        binding.viewPager.adapter = LearningProblemViewPagerAdapter(supportFragmentManager, lifecycle)

        setContentView(binding.root)
    }

    fun stopSwiping(){
        binding.viewPager.isUserInputEnabled = false
    }

    fun startSwiping(){
        binding.viewPager.isUserInputEnabled = true
    }
}