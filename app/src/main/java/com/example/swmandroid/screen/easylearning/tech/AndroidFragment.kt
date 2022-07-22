package com.example.swmandroid.screen.easylearning.tech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swmandroid.databinding.FragmentTechBinding

class AndroidFragment : Fragment() {

    private var _binding: FragmentTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTechBinding.inflate(inflater, container, false)
        binding.startTechTextview.text = "안드로이드 \n학습을 시작하시겠습니까?"
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}