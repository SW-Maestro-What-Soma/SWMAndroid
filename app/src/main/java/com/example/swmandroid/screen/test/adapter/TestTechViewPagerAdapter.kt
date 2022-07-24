package com.example.swmandroid.screen.test.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.screen.easylearning.tech.NoTechFragment
import com.example.swmandroid.screen.easylearning.tech.LearningTechFragment

class TestTechViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object{
        const val NUM_TABS = 11
    }

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> NoTechFragment()
        1 -> LearningTechFragment("백엔드")
        2 -> LearningTechFragment("프론트엔드")
        3 ->  LearningTechFragment("안드로이드")
        4 -> LearningTechFragment("IOS")
        5 -> LearningTechFragment("데이터사이언스")
        6 -> LearningTechFragment("데이터분석")
        7 -> LearningTechFragment("알고리즘")
        8 -> LearningTechFragment("자료구조")
        9 ->  LearningTechFragment("네트워크")
        10 -> LearningTechFragment("운영체제")
        else -> NoTechFragment()
    }

}