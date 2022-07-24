package com.example.swmandroid.screen.easylearning.problem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.swmandroid.databinding.FragmentLearningProblemBinding
import com.example.swmandroid.screen.easylearning.EasyLearningViewModel
import com.example.swmandroid.screen.easylearning.StartEasyLearningActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class LearningProblemFragment() : Fragment() {

    companion object {
        private const val KEY_PROBLEM_NUMBER = "problem_number"

        @JvmStatic
        fun newInstance(problemNumber: Int) =
            LearningProblemFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PROBLEM_NUMBER, problemNumber)
                }
            }
    }

    private var _binding: FragmentLearningProblemBinding? = null
    private val binding get() = _binding!!

    private val viewModel by sharedViewModel<EasyLearningViewModel>()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLearningProblemBinding.inflate(inflater, container, false)

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

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        checkAnswer.visibility = View.INVISIBLE
                        checkProblemContent.visibility = View.VISIBLE
                        (activity as StartEasyLearningActivity).startSwiping()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        checkAnswer.visibility = View.VISIBLE
                        checkProblemContent.visibility = View.INVISIBLE
                        (activity as StartEasyLearningActivity).startSwiping()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        (activity as StartEasyLearningActivity).stopSwiping()
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}