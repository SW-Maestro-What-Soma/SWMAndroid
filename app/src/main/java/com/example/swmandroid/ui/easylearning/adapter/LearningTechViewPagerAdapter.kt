package com.example.swmandroid.ui.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.ui.easylearning.tech.LearningTechFragment
import com.example.swmandroid.ui.easylearning.tech.NoTechFragment

class LearningTechViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val NUM_TABS = 11
    }

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> NoTechFragment()
        1 -> LearningTechFragment.newInstance("백엔드")
        2 -> LearningTechFragment.newInstance("프론트엔드")
        3 -> LearningTechFragment.newInstance("안드로이드")
        4 -> LearningTechFragment.newInstance("IOS")
        5 -> LearningTechFragment.newInstance("데이터사이언스")
        6 -> LearningTechFragment.newInstance("데이터분석")
        7 -> LearningTechFragment.newInstance("알고리즘")
        8 -> LearningTechFragment.newInstance("자료구조")
        9 -> LearningTechFragment.newInstance("네트워크")
        else -> LearningTechFragment.newInstance("운영체제")
    }
}