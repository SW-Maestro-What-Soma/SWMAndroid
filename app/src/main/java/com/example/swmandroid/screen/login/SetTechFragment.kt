package com.example.swmandroid.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentSetTechBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SetTechFragment : Fragment() {
    private var _binding: FragmentSetTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetTechBinding.inflate(inflater, container, false)

        buttonClick()

        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}