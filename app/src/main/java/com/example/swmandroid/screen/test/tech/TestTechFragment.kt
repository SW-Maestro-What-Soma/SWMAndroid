package com.example.swmandroid.screen.test.tech

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swmandroid.databinding.FragmentTestTechBinding
import com.example.swmandroid.screen.test.StartTestActivity

class TestTechFragment : Fragment() {

    companion object {
        private const val KEY_TECH_STACK = "learning_tech"

        @JvmStatic
        fun newInstance(techStack: String) =
            TestTechFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TECH_STACK, techStack)
                }
            }
    }

    private var _binding: FragmentTestTechBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTestTechBinding.inflate(inflater, container, false)

        arguments?.let{
            binding.tech = it.getString(KEY_TECH_STACK)
        }

        binding.startTestButton.setOnClickListener {
            val intent = Intent(activity, StartTestActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}