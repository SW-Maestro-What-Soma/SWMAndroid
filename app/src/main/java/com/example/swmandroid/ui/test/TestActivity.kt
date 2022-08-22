package com.example.swmandroid.ui.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityTestBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.easylearning.tech.NoTechFragment
import com.example.swmandroid.ui.test.tech.TestTechFragment
import com.google.android.material.tabs.TabLayoutMediator

class TestActivity : BaseActivity<ActivityTestBinding>({ActivityTestBinding.inflate(it)}) {

    private val mainTabArray = arrayOf("", "백엔드", "프론트엔드", "안드로이드", "IOS", "데이타사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        buttonClick()
    }

    private fun initView() = with(binding){
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()

        techTablayout.getTabAt(0)?.view?.visibility = View.GONE
    }

    private fun initFragments(): List<Fragment> = listOf(
        NoTechFragment(),
        TestTechFragment.newInstance("Backend"),
        TestTechFragment.newInstance("Frontend"),
        TestTechFragment.newInstance("Android"),
        TestTechFragment.newInstance("IOS"),
        TestTechFragment.newInstance("DataScience"),
        TestTechFragment.newInstance("DataAnalysis"),
        TestTechFragment.newInstance("Algorithm"),
        TestTechFragment.newInstance("DataStructure"),
        TestTechFragment.newInstance("Network"),
        TestTechFragment.newInstance("OperatingSystem"),
    )

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener {
            finish()
        }
    }
}