package com.example.swmandroid.screen.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.screen.easylearning.EasyLearningViewModel
import com.example.swmandroid.screen.easylearning.problem.LearningProblemFragment

//TODO 학습 문제 수 만큼 생성
class LearningProblemViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return LearningProblemFragment.newInstance(position)
    }

}