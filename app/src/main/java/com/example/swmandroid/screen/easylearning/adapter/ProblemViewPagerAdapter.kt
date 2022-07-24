package com.example.swmandroid.screen.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.swmandroid.screen.easylearning.EasyLearningViewModel
import com.example.swmandroid.screen.easylearning.problem.ProblemFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//TODO 문제 수 만큼 생성
class ProblemViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val viewModel : EasyLearningViewModel) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment  {
        viewModel.setProblemNumber(position)
        return ProblemFragment()
    }

}