package com.example.swmandroid.ui.easylearning

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartEasyLearningBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.easylearning.problem.LearningProblemFragment

class StartEasyLearningActivity : BaseActivity<ActivityStartEasyLearningBinding>({ ActivityStartEasyLearningBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)
    }

    private fun initFragments(): List<Fragment> = listOf(
        LearningProblemFragment.newInstance(0),
        LearningProblemFragment.newInstance(1),
        LearningProblemFragment.newInstance(2)
    )

    fun stopSwiping() {
        binding.viewPager.isUserInputEnabled = false
    }

    fun startSwiping() {
        binding.viewPager.isUserInputEnabled = true
    }
}