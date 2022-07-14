package com.example.swmandroid.screen.easylearning.maintech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swmandroid.databinding.FragmentMainTechBinding
import com.example.swmandroid.screen.easylearning.adapter.SubViewPagerAdapter

class FrontEndFragment : Fragment() {

    private var _binding: FragmentMainTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainTechBinding.inflate(inflater, container, false)

        val viewPager = binding.viewPager
        val tabLayout = binding.subTechTablayout
        tabLayout.visibility = View.INVISIBLE

        viewPager.adapter = SubViewPagerAdapter(childFragmentManager, lifecycle, "Frontend", 1)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}