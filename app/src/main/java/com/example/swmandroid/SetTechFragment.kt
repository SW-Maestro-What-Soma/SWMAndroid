package com.example.swmandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.databinding.FragmentSetTechBinding

class SetTechFragment : Fragment() {

    private var _binding: FragmentSetTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetTechBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener { binding.root.findNavController().popBackStack() }
        binding.techFrontendButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_setTechFragment_to_setNickFragment) }
        binding.techBackendButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_setTechFragment_to_setNickFragment) }
        binding.techAndroidButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_setTechFragment_to_setNickFragment) }
        binding.techIosButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_setTechFragment_to_setNickFragment) }
        binding.techDataButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_setTechFragment_to_setNickFragment) }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}