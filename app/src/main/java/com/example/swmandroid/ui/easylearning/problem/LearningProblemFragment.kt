package com.example.swmandroid.ui.easylearning.problem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentLearningProblemBinding
import com.example.swmandroid.model.problem.ProblemResponseItem
import com.example.swmandroid.ui.easylearning.EasyLearningViewModel
import com.example.swmandroid.ui.easylearning.StartEasyLearningActivity
import com.example.swmandroid.util.ScreenMetricsUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LearningProblemFragment : BaseFragment<FragmentLearningProblemBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLearningProblemBinding {
        return FragmentLearningProblemBinding.inflate(inflater, container, false)
    }

    companion object {
        private const val KEY_PROBLEM_NUMBER = "problem_number"
        private const val KEY_PROBLEM = "problem"

        @JvmStatic
        fun newInstance(problemNumber: Int, problem: ProblemResponseItem) =
            LearningProblemFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PROBLEM_NUMBER, problemNumber)
                    putParcelable(KEY_PROBLEM, problem)
                }
            }
    }

    private val easyLearningViewModel: EasyLearningViewModel by sharedViewModel()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        buttonClick()
    }

    private fun initView() = with(binding) {
        val displaySize = ScreenMetricsUtil.getScreenSize(requireActivity())
        val displayHeight = displaySize.height

        bottomSheet.layoutParams.height = (displayHeight * 0.7).toInt()

        easyLearningViewModel.isFavoriteLearningProblem.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> favoriteButton.setImageResource(R.drawable.selected_favorite)
                false -> favoriteButton.setImageResource(R.drawable.unselected_favorite)
            }
        })

        favoriteButton.setOnClickListener {
            easyLearningViewModel.setFavoriteLearningProblem()
        }


        arguments?.let {
            val problemNumber = it.getInt(KEY_PROBLEM_NUMBER)
            val problem = it.getParcelable<ProblemResponseItem>(KEY_PROBLEM)

            problemNumberTextview.text = getString(R.string.problem_number, problemNumber)
            problemTitleTextview.text = problem?.text
            problemContentTextview.text = problem?.modelAnswer

            setArrowVisibility(problemNumber)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheetBackground.setImageResource(R.drawable.bottom_sheet)
                        updownImageview.setImageResource(R.drawable.down)
                        checkAnswerTextview.visibility = View.INVISIBLE
                        problemContentTextview.visibility = View.VISIBLE
                        (activity as StartEasyLearningActivity).startSwiping()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottomSheetBackground.setImageResource(R.drawable.hide_bottom_sheet)
                        updownImageview.setImageResource(R.drawable.up)
                        checkAnswerTextview.visibility = View.VISIBLE
                        problemContentTextview.visibility = View.INVISIBLE
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

    private fun setArrowVisibility(number : Int) = with(binding){
        if(number == 1){
            leftButton.visibility = View.INVISIBLE
        }
    }

    private fun buttonClick() = with(binding) {
        closeButton.setOnClickListener {
            activity?.finish()
        }

        rightButton.setOnClickListener {
            (activity as StartEasyLearningActivity).moveNextProblem()
        }

        leftButton.setOnClickListener {
            (activity as StartEasyLearningActivity).movePrevProblem()
        }
    }

}

