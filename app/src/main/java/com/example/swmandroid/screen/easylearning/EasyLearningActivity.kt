package com.example.swmandroid.screen.easylearning

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.swmandroid.databinding.ActivityEasyLearningBinding
import com.example.swmandroid.screen.easylearning.adapter.TechViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class EasyLearningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEasyLearningBinding
    private val mainTabArray = arrayOf("","백엔드", "프론트엔드", "안드로이드", "IOS", "데이타사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityEasyLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.techTablayout

        viewPager.adapter = TechViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.setCurrentItem(0, false)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()

        tabLayout.getTabAt(0)?.view?.visibility = View.GONE
    }
}