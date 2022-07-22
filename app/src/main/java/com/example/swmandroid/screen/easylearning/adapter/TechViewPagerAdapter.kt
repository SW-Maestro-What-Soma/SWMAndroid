package com.example.swmandroid.screen.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.screen.easylearning.tech.*

private const val NUM_TABS = 10

class TechViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> BackEndFragment()
        1 -> FrontEndFragment()
        2 -> AndroidFragment()
        3 -> IOSFragment()
        4 -> ScienceFragment()
        5 -> AnalysisFragment()
        6 -> AlgorithmFragment()
        7 -> DataStructureFragment()
        8 -> NetworkFragment()
        9 -> OperatingSystemFragment()
        else -> BackEndFragment()
    }
}