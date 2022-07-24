package com.example.swmandroid.ui.test.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.ui.easylearning.tech.NoTechFragment
import com.example.swmandroid.ui.test.tech.TestTechFragment

class TestTechViewPagerAdapter(
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
        1 -> TestTechFragment.newInstance("백엔드")
        2 -> TestTechFragment.newInstance("프론트엔드")
        3 -> TestTechFragment.newInstance("안드로이드")
        4 -> TestTechFragment.newInstance("IOS")
        5 -> TestTechFragment.newInstance("데이터사이언스")
        6 -> TestTechFragment.newInstance("데이터분석")
        7 -> TestTechFragment.newInstance("알고리즘")
        8 -> TestTechFragment.newInstance("자료구조")
        9 -> TestTechFragment.newInstance("네트워크")
        else -> TestTechFragment.newInstance("운영체제")
    }
}