package com.example.swmandroid.screen.easylearning.problem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.swmandroid.databinding.FragmentProblemBinding
import com.example.swmandroid.screen.easylearning.EasyLearningViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProblemFragment() : Fragment() {

    private var _binding: FragmentProblemBinding? = null
    private val binding get() = _binding!!

    private val viewModel by sharedViewModel<EasyLearningViewModel>()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProblemBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() = with(binding) {

        viewModel.getProblem()

        val problemNumber = viewModel.problemNumber.value
        val problem = viewModel.problem.value?.get(problemNumber!!)

        bindingProblem = problem
        bindingProblemNumber = problemNumber!! + 1

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        checkAnswer.visibility = View.INVISIBLE
                        checkProblemContent.visibility = View.VISIBLE
                        viewModel.setIsSwiping(true)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        checkAnswer.visibility = View.VISIBLE
                        checkProblemContent.visibility = View.INVISIBLE
                        viewModel.setIsSwiping(true)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        viewModel.setIsSwiping(false)
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