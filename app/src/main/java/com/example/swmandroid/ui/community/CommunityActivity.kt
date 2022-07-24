package com.example.swmandroid.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityCommunityBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class CommunityActivity : BaseActivity<ActivityCommunityBinding>({ ActivityCommunityBinding.inflate(it) }) {

    private val mainTabArray = arrayOf("전체", "채용공고", "채용후기", "스터디", "질문")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding){
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)

        TabLayoutMediator(communityTablayout, viewPager) {tab, position ->
            tab.text = mainTabArray[position]
        }.attach()
    }

    private fun initFragments() : List<Fragment> = listOf(
        FullCommunityFragment(),
        SubCommunityFragment.newInstance("채용공고"),
        SubCommunityFragment.newInstance("채용후기"),
        SubCommunityFragment.newInstance("스터디"),
        SubCommunityFragment.newInstance("질문"),
    )
}