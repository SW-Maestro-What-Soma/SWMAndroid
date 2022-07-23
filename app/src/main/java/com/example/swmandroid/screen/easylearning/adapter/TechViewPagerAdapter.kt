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
        0 -> NoSelectFragment()
        1 -> BackEndFragment()
        2 -> FrontEndFragment()
        3 ->  AndroidFragment()
        4 -> IOSFragment()
        5 -> ScienceFragment()
        6 -> AnalysisFragment()
        7 -> AlgorithmFragment()
        8 -> DataStructureFragment()
        9 ->  NetworkFragment()
        10 -> OperatingSystemFragment()
        else -> NoSelectFragment()
    }
}