package com.example.swmandroid.screen.easylearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swmandroid.databinding.ActivityEasyLearningBinding
import com.example.swmandroid.screen.easylearning.adapter.MainViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class EasyLearningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEasyLearningBinding
    private val mainTabArray = arrayOf("백엔드", "프론트엔드", "모바일", "데이타", "공통")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasyLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.mainTechTablayout

        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()
    }
}