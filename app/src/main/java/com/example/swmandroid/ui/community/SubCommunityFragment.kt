package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSubCommunityBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SubCommunityFragment : BaseFragment<FragmentSubCommunityBinding>() {

    companion object {
        private const val KEY_COMMUNITY = "community"

        @JvmStatic
        fun newInstance(community : String) =
            SubCommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_COMMUNITY, community)
                }
            }
    }

    private val mainTabArray = arrayOf("백엔드", "프론트엔드", "안드로이드", "IOS", "데이터사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSubCommunityBinding {
        return FragmentSubCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragments)

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()
    }

    private fun initFragments() : List<Fragment> = listOf(
        TechCommunityFragment.newInstance("백엔드"),
        TechCommunityFragment.newInstance("프론트엔드"),
        TechCommunityFragment.newInstance("안드로이드"),
        TechCommunityFragment.newInstance("IOS"),
        TechCommunityFragment.newInstance("데이터사이언스"),
        TechCommunityFragment.newInstance("데이터분석"),
        TechCommunityFragment.newInstance("알고리즘"),
        TechCommunityFragment.newInstance("자료구조"),
        TechCommunityFragment.newInstance("네트워크"),
        TechCommunityFragment.newInstance("운영체제")
    )
}