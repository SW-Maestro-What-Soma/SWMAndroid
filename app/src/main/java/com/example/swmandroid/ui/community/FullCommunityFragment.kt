package com.example.swmandroid.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFullCommunityBinding

class FullCommunityFragment : BaseFragment<FragmentFullCommunityBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFullCommunityBinding {
        return FragmentFullCommunityBinding.inflate(inflater, container, false)
    }

    fun moveSubCommunityFragment(category : String){
        val action = FullCommunityFragmentDirections.actionFullCommunityFragmentToSubCommunityFragment(category)
        binding.root.findNavController().navigate(action)
    }

}