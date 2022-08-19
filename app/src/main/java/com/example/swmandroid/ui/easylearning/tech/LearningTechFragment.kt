package com.example.swmandroid.ui.easylearning.tech

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentLearningTechBinding
import com.example.swmandroid.ui.easylearning.StartEasyLearningActivity

class LearningTechFragment : BaseFragment<FragmentLearningTechBinding>() {

    companion object {
        private const val KEY_TECH_STACK = "tech_stack"

        @JvmStatic
        fun newInstance(techStack: String) =
            LearningTechFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TECH_STACK, techStack)
                }
            }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLearningTechBinding {
        return FragmentLearningTechBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.startTechTextview.text = getString(R.string.start_learning_tech, it.getString(KEY_TECH_STACK))
        }

        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        startLearningButton.setOnClickListener {
            val intent = Intent(activity, StartEasyLearningActivity::class.java)
            startActivity(intent)
        }


    }
}