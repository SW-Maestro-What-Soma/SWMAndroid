package com.example.swmandroid.ui.easylearning

import android.os.Bundle
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartEasyLearningBinding
import com.example.swmandroid.ui.easylearning.adapter.LearningProblemViewPagerAdapter

class StartEasyLearningActivity : BaseActivity<ActivityStartEasyLearningBinding>({ ActivityStartEasyLearningBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = LearningProblemViewPagerAdapter(supportFragmentManager, lifecycle)
    }

    fun stopSwiping(){
        binding.viewPager.isUserInputEnabled = false
    }

    fun startSwiping(){
        binding.viewPager.isUserInputEnabled = true
    }
}