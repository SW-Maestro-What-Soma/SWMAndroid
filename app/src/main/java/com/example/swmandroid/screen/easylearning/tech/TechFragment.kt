package com.example.swmandroid.screen.easylearning.tech

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swmandroid.databinding.FragmentTechBinding
import com.example.swmandroid.screen.easylearning.EasyLearningViewModel
import com.example.swmandroid.screen.easylearning.StartEasyLearningActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TechFragment(private val techStack: String) : Fragment(){

    private var _binding : FragmentTechBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<EasyLearningViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTechBinding.inflate(inflater, container, false)

        binding.easyLearningViewModel = viewModel
        viewModel.selectTechStack(techStack)

        binding.startLearningButton.setOnClickListener {
            val intent = Intent(activity, StartEasyLearningActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}