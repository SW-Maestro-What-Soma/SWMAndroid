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

    private val fragments = listOf(
        TechCommunityFragment.newInstance("Backend"),
        TechCommunityFragment.newInstance("Frontend"),
        TechCommunityFragment.newInstance("Android"),
        TechCommunityFragment.newInstance("IOS"),
        TechCommunityFragment.newInstance("DataScience"),
        TechCommunityFragment.newInstance("DataAnalysis"),
        TechCommunityFragment.newInstance("Algorithm"),
        TechCommunityFragment.newInstance("DataStructure"),
        TechCommunityFragment.newInstance("Network"),
        TechCommunityFragment.newInstance("OperatingSystem"),
    )

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