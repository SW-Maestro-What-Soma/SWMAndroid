package com.example.swmandroid.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentSetNickBinding

class SetNickFragment : Fragment() {

    private var _binding: FragmentSetNickBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetNickBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener { binding.root.findNavController().popBackStack() }
        binding.setNickButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_setNickFragment_to_mainActivity) }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}