package com.example.swmandroid.screen.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.screen.easylearning.maintech.*

private const val NUM_TABS = 5

class MainViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return BackEndFragment()
            1 -> return FrontEndFragment()
            2 -> return MobileFragment()
            3 -> return DataFragment()
            4 -> return CommonFragment()
        }
        return BackEndFragment()
    }

}