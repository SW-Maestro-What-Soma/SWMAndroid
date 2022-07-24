package com.example.swmandroid.ui.test.tech

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentTestTechBinding
import com.example.swmandroid.ui.test.StartTestActivity

class TestTechFragment : BaseFragment<FragmentTestTechBinding>() {

    companion object {
        private const val KEY_TECH_STACK = "tech_stack"

        @JvmStatic
        fun newInstance(techStack: String) =
            TestTechFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TECH_STACK, techStack)
                }
            }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTestTechBinding {
        return FragmentTestTechBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            binding.tech = it.getString(KEY_TECH_STACK)
        }

        buttonClick()
    }

    private fun buttonClick() = with(binding){
        startTestButton.setOnClickListener {
            val intent = Intent(activity, StartTestActivity::class.java)
            startActivity(intent)
        }
    }
}