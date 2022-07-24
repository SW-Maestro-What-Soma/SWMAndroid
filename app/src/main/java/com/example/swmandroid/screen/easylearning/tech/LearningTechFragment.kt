package com.example.swmandroid.screen.easylearning.tech

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swmandroid.databinding.FragmentLearningTechBinding
import com.example.swmandroid.screen.easylearning.StartEasyLearningActivity

class LearningTechFragment() : Fragment() {

    companion object {
        private const val KEY_TECH_STACK = "learning_tech"

        @JvmStatic
        fun newInstance(techStack: String) =
            LearningTechFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TECH_STACK, techStack)
                }
            }
    }

    private var _binding: FragmentLearningTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLearningTechBinding.inflate(inflater, container, false)

        arguments?.let {
            binding.tech = it.getString(KEY_TECH_STACK)
        }

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