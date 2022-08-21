package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSubCommunityBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SubCommunityFragment : BaseFragment<FragmentSubCommunityBinding>() {

    private val mainTabArray = arrayOf("백엔드", "프론트엔드", "안드로이드", "IOS", "데이터사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    private var techStack = ""
    private var category = ""

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSubCommunityBinding {
        return FragmentSubCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        buttonClick()
    }

    private fun initView() = with(binding) {
        val fragments = initFragments()
        viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragments)

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()
    }

    private fun initFragments(): List<Fragment> {
        val args: SubCommunityFragmentArgs by navArgs()
        category = args.category

        return listOf(
            TechCommunityFragment.newInstance("백엔드", category),
            TechCommunityFragment.newInstance("프론트엔드", category),
            TechCommunityFragment.newInstance("안드로이드", category),
            TechCommunityFragment.newInstance("IOS", category),
            TechCommunityFragment.newInstance("데이터사이언스", category),
            TechCommunityFragment.newInstance("데이터분석", category),
            TechCommunityFragment.newInstance("알고리즘", category),
            TechCommunityFragment.newInstance("자료구조", category),
            TechCommunityFragment.newInstance("네트워크", category),
            TechCommunityFragment.newInstance("운영체제", category),
        )
    }

    private fun buttonClick() = with(binding) {
        searchLayout.setOnClickListener {
            setTechStack()
            val action = SubCommunityFragmentDirections.actionSubCommunityFragmentToSearchCommunityFragment(techStack, category)
            root.findNavController().navigate(action)
        }
    }

    private fun setTechStack() {
        techStack = mainTabArray[binding.viewPager.currentItem]
    }

    fun moveFullCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_subCommunityFragment_to_fullCommunityFragment)
    }

    fun moveSubCommunityFragment(category: String) {
        val action = SubCommunityFragmentDirections.actionSubCommunityFragmentSelf(category)
        binding.root.findNavController().navigate(action)
    }
}