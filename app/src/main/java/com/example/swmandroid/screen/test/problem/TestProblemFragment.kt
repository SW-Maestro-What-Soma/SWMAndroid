package com.example.swmandroid.screen.test.problem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swmandroid.databinding.FragmentTestProblemBinding
import com.example.swmandroid.screen.test.TestViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TestProblemFragment : Fragment() {

    companion object {
        private const val KEY_PROBLEM_NUMBER = "problem_number"

        @JvmStatic
        fun newInstance(problemNumber: Int) =
            TestProblemFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PROBLEM_NUMBER, problemNumber)
                }
            }
    }

    private var _binding: FragmentTestProblemBinding? = null
    private val binding get() = _binding!!

    private val viewModel by sharedViewModel<TestViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTestProblemBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() = with(binding) {

        viewModel.getProblem()

        arguments?.let {
            val problemNumber = it.getInt(KEY_PROBLEM_NUMBER)
            val problem = viewModel.problem.value?.get(problemNumber)

            bindingProblem = problem
            bindingProblemNumber = problemNumber + 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}