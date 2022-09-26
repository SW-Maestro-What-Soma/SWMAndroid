package com.example.swmandroid.ui.community

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSubCommunityBinding
import com.example.swmandroid.ui.community.adapter.CommunityViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SubCommunityFragment : BaseFragment<FragmentSubCommunityBinding>() {

    private lateinit var onBackCallBack: OnBackPressedCallback

    private val mainTabArray = arrayOf("백엔드", "프론트엔드", "안드로이드", "IOS", "데이터사이언스", "데이터분석", "알고리즘", "자료구조", "네트워크", "운영체제")

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSubCommunityBinding {
        return FragmentSubCommunityBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initCallBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onDetach() {
        super.onDetach()

        onBackCallBack.remove()
    }

    private fun initCallBack() {
        onBackCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as CommunityActivity).setTopCategoryPosition(0)
                moveFullCommunityFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackCallBack)
    }

    private fun initView() = with(binding) {
        viewPager.adapter = CommunityViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(techTablayout, viewPager) { tab, position ->
            tab.text = mainTabArray[position]
        }.attach()
    }

    fun moveFullCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_subCommunityFragment_to_fullCommunityFragment)
    }

    fun moveSubCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_subCommunityFragment_self)
    }

}