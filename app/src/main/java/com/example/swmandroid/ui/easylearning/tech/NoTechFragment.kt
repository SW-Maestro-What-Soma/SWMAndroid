package com.example.swmandroid.ui.easylearning.tech

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentNoTechBinding

class NoTechFragment : BaseFragment<FragmentNoTechBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentNoTechBinding {
        return FragmentNoTechBinding.inflate(inflater, container, false)
    }

}