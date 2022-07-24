package com.example.swmandroid.ui.easylearning

import android.os.Bundle
import android.view.View
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityEasyLearningBinding
import com.example.swmandroid.ui.easylearning.adapter.LearningTechViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class EasyLearningActivity : BaseActivity<ActivityEasyLearningBinding>({ActivityEasyLearningBinding.inflate(it)}) {

    private val mainTabArray = arrayOf("", "백엔드", "프론트엔드", "안드로이드", "IOS", "데이타사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        viewPager.adapter = LearningTechViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()

        techTablayout.getTabAt(0)?.view?.visibility = View.GONE
    }
}