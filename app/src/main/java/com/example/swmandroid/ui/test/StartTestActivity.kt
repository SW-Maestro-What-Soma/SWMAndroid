package com.example.swmandroid.ui.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartTestBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.test.problem.TestProblemFragment

class StartTestActivity : BaseActivity<ActivityStartTestBinding>({ ActivityStartTestBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)
    }

    private fun initFragments(): List<Fragment> = listOf(
        TestProblemFragment.newInstance(0),
        TestProblemFragment.newInstance(1),
        TestProblemFragment.newInstance(2)
    )
}