package com.example.swmandroid.screen.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.screen.easylearning.tech.*

private const val NUM_TABS = 11

class TechViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> NoTechFragment()
        1 -> TechFragment("백엔드")
        2 -> TechFragment("프론트엔드")
        3 ->  TechFragment("안드로이드")
        4 -> TechFragment("IOS")
        5 -> TechFragment("데이터사이언스")
        6 -> TechFragment("데이터분석")
        7 -> TechFragment("알고리즘")
        8 -> TechFragment("자료구조")
        9 ->  TechFragment("네트워크")
        10 -> TechFragment("운영체제")
        else -> NoTechFragment()
    }
}