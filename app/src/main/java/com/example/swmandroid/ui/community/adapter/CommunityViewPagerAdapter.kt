package com.example.swmandroid.ui.community.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.ui.community.TechCommunityFragment

class CommunityViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentTags = listOf(
        "Backend",
        "Frontend",
        "Android",
        "IOS",
        "DataScience",
        "DataAnalysis",
        "Algorithm",
        "DataStructure",
        "Network",
        "OperatingSystem"
    )

    override fun getItemCount(): Int {
        return fragmentTags.size
    }

    override fun createFragment(position: Int): Fragment {
        return TechCommunityFragment.newInstance(fragmentTags[position])
    }

}