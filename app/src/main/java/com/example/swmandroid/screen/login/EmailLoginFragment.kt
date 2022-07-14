package com.example.swmandroid.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentEmailLoginBinding

class EmailLoginFragment : Fragment() {

    private var _binding: FragmentEmailLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var isFirst = true

        _binding = FragmentEmailLoginBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener { binding.root.findNavController().popBackStack() }
        binding.loginButton.setOnClickListener {
            if (isFirst) {
                binding.root.findNavController().navigate(R.id.action_emailLoginFragment_to_setTechFragment)
            }else{
                binding.root.findNavController().navigate(R.id.action_emailLoginFragment_to_mainActivity)
            }
        }
        binding.signInTextview.setOnClickListener { binding.root.findNavController().navigate(R.id.action_emailLoginFragment_to_signInFragment) }
        binding.findPasswordTextview.setOnClickListener { binding.root.findNavController().navigate(R.id.action_emailLoginFragment_to_findPasswordFragment) }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}