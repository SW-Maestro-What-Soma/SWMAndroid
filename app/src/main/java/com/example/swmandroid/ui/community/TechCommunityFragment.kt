package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentTechCommunityBinding

class TechCommunityFragment : BaseFragment<FragmentTechCommunityBinding>() {

    companion object{
        private const val KEY_TECH_COMMUNITY = "tech_stack"

        @JvmStatic
        fun newInstance(techStack : String) =
            TechCommunityFragment().apply {
                arguments = Bundle().apply{
                    putString(KEY_TECH_COMMUNITY, techStack)
                }
            }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTechCommunityBinding {
        return FragmentTechCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.test.text = arguments?.getString(KEY_TECH_COMMUNITY)
    }

}