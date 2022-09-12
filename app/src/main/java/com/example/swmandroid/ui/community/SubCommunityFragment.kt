package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSubCommunityBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SubCommunityFragment : BaseFragment<FragmentSubCommunityBinding>() {

    private val mainTabArray = arrayOf("백엔드", "프론트엔드", "안드로이드", "IOS", "데이터사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSubCommunityBinding {
        return FragmentSubCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, fragments)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val techStack = getTechStack(mainTabArray[position])
                communityViewModel.setTechStack(techStack)
            }
        })

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()
    }

    private fun initFragments(): List<Fragment> {
        return listOf(
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
            TechCommunityFragment(),
        )
    }

    fun moveFullCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_subCommunityFragment_to_fullCommunityFragment)
    }

    fun moveSubCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_subCommunityFragment_self)
    }

    private fun getTechStack(techStack: String): String =
        when (techStack) {
            "백엔드" -> "Backend"
            "프론트엔드" -> "Frontend"
            "안드로이드" -> "Android"
            "IOS" -> "IOS"
            "데이터사이언스" -> "DataScience"
            "데이터분석" -> "DataAnalysis"
            "알고리즘" -> "Algorithm"
            "자료구조" -> "DataStructure"
            "네트워크" -> "Network"
            "운영체제" -> "OperatingSystem"
            else -> "Backend"
        }

}