package com.example.swmandroid.screen.easylearning.subtech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swmandroid.databinding.FragmentSubTechBinding

class OperatingSystemFragment : Fragment() {
    private var _binding: FragmentSubTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubTechBinding.inflate(inflater, container, false)
        binding.subStartTextview.text = "운영체제 \n학습을 시작하시겠습니까?"
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}