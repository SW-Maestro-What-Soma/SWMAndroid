package com.example.swmandroid.screen.easylearning.maintech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swmandroid.databinding.FragmentMainTechBinding
import com.example.swmandroid.screen.easylearning.adapter.SubViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class CommonFragment : Fragment() {

    private var _binding: FragmentMainTechBinding? = null
    private val binding get() = _binding!!
    private val subTabArray = arrayOf("알고리즘", "자료구조", "네트워크", "운영체제")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainTechBinding.inflate(inflater, container, false)

        val viewPager = binding.viewPager
        val tabLayout = binding.subTechTablayout
        tabLayout.visibility = View.VISIBLE

        viewPager.adapter = SubViewPagerAdapter(childFragmentManager, lifecycle, "Common",4)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = subTabArray[position]
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}