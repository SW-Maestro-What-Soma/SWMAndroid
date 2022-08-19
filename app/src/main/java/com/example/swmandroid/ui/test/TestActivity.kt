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
        TestTechFragment.newInstance("백엔드"),
        TestTechFragment.newInstance("프론트엔드"),
        TestTechFragment.newInstance("안드로이드"),
        TestTechFragment.newInstance("IOS"),
        TestTechFragment.newInstance("데이터사이언스"),
        TestTechFragment.newInstance("데이터분석"),
        TestTechFragment.newInstance("알고리즘"),
        TestTechFragment.newInstance("자료구조"),
        TestTechFragment.newInstance("네트워크"),
        TestTechFragment.newInstance("운영체제"),
    )

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener {
            finish()
        }
    }
}