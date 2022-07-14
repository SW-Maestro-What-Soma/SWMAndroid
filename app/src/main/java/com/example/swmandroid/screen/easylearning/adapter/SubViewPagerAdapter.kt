package com.example.swmandroid.screen.easylearning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.swmandroid.screen.easylearning.maintech.BackEndFragment
import com.example.swmandroid.screen.easylearning.subtech.*

class SubViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, category: String, categoryCnt: Int) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val category = category
    private val categoryCnt = categoryCnt

    override fun getItemCount(): Int {
        return categoryCnt
    }

    override fun createFragment(position: Int): Fragment {
        when (category) {
            "Backend" -> return SubBackendFragment()
            "Frontend" -> return SubFrontendFragment()
            "Mobile" -> {
                when(position){
                    0 -> return AndroidFragment()
                    1 -> return IOSFragment()
                }
                return AndroidFragment()
            }
            "Data" -> {
                when(position){
                    0 -> return ScienceFragment()
                    1 -> return AnalysisFragment()
                }
                return ScienceFragment()
            }
            "Common" -> {
                when(position){
                    0 -> return AlgorithmFragment()
                    1 -> return DataStructureFragment()
                    2 -> return NetworkFragment()
                    3 -> return OperatingSystemFragment()
                }
                return AlgorithmFragment()
            }
            else -> return BackEndFragment()
        }
    }
}