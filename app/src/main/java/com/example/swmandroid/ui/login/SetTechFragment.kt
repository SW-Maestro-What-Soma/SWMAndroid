package com.example.swmandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSetTechBinding

class SetTechFragment : BaseFragment<FragmentSetTechBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSetTechBinding {
        return FragmentSetTechBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        techFrontendButton.setOnClickListener { moveSetNickFragment("Frontend") }
        techBackendButton.setOnClickListener { moveSetNickFragment("Backend") }
        techAndroidButton.setOnClickListener { moveSetNickFragment("Android") }
        techIosButton.setOnClickListener { moveSetNickFragment("IOS") }
        techDataButton.setOnClickListener { moveSetNickFragment("Data") }
    }

    private fun moveSetNickFragment(techStack: String) {
        binding.root.findNavController().navigate(R.id.action_setTechFragment_to_setNickFragment, bundleOf("tech_stack" to techStack))
    }

}