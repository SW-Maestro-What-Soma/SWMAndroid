package com.example.swmandroid.ui.easylearning

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityEasyLearningBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.easylearning.tech.LearningTechFragment
import com.example.swmandroid.ui.easylearning.tech.NoTechFragment
import com.google.android.material.tabs.TabLayoutMediator

class EasyLearningActivity : BaseActivity<ActivityEasyLearningBinding>({ ActivityEasyLearningBinding.inflate(it) }) {

    private val mainTabArray = arrayOf("", "백엔드", "프론트엔드", "안드로이드", "IOS", "데이타사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        buttonClick()
    }

    private fun initView() = with(binding) {
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()

        techTablayout.getTabAt(0)?.view?.visibility = View.GONE
    }

    private fun initFragments(): List<Fragment> = listOf(
        NoTechFragment(),
        LearningTechFragment.newInstance("Backend"),
        LearningTechFragment.newInstance("Frontend"),
        LearningTechFragment.newInstance("Android"),
        LearningTechFragment.newInstance("IOS"),
        LearningTechFragment.newInstance("DataScience"),
        LearningTechFragment.newInstance("DataAnalysis"),
        LearningTechFragment.newInstance("Algorithm"),
        LearningTechFragment.newInstance("DataStructure"),
        LearningTechFragment.newInstance("Network"),
        LearningTechFragment.newInstance("OperatingSystem"),
    )

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener {
            finish()
        }
    }
}