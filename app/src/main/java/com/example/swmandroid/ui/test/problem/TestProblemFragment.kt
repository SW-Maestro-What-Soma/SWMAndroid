package com.example.swmandroid.ui.test.problem

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentTestProblemBinding
import com.example.swmandroid.model.problem.ProblemResponseItem
import com.example.swmandroid.ui.test.StartTestActivity
import com.example.swmandroid.ui.test.TestResultActivity
import com.example.swmandroid.ui.test.TestViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class TestProblemFragment : BaseFragment<FragmentTestProblemBinding>() {

    companion object {
        private const val KEY_PROBLEM_NUMBER = "problem_number"
        private const val KEY_PROBLEM = "problem"

        @JvmStatic
        fun newInstance(problemNumber: Int, problem: ProblemResponseItem) =
            TestProblemFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PROBLEM_NUMBER, problemNumber)
                    putParcelable(KEY_PROBLEM, problem)
                }
            }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTestProblemBinding {
        return FragmentTestProblemBinding.inflate(inflater, container, false)
    }

    private val testViewModel: TestViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        buttonClick()
    }

    private fun initView() = with(binding) {
        object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minute = millisUntilFinished / 60000
                val second = millisUntilFinished % 60000 / 1000
                timer.text = getString(R.string.timer, minute, second)
            }

            override fun onFinish() {
                Toast.makeText(requireActivity(), "끝났습니다", Toast.LENGTH_SHORT).show()
            }

        }.start()

        testViewModel.isFavoriteTestProblem.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> favoriteButton.setImageResource(R.drawable.selected_favorite)
                false -> favoriteButton.setImageResource(R.drawable.unselected_favorite)
            }
        })

        favoriteButton.setOnClickListener {
            testViewModel.setFavoriteTestProblem()
        }

        arguments?.let {
            val problemNumber = it.getInt(KEY_PROBLEM_NUMBER)
            val problem = it.getParcelable<ProblemResponseItem>(KEY_PROBLEM)

            leftProblemTextview.text = getString(R.string.left_problem, 11 - problemNumber)

            problemNumberTextview.text = getString(R.string.problem_number, problemNumber)
            problemTitleTextview.text = problem?.text

            setArrowVisibility(problemNumber)
        }
    }

    private fun setArrowVisibility(number: Int) = with(binding) {
        when (number) {
            1 -> {
                leftButton.visibility = View.INVISIBLE
                submissionButton.visibility = View.INVISIBLE
            }
            10 -> {
                rightButton.visibility = View.INVISIBLE
                submissionButton.visibility = View.VISIBLE
            }
            else -> {
                submissionButton.visibility = View.INVISIBLE
            }
        }
    }

    private fun buttonClick() = with(binding) {
        closeButton.setOnClickListener {
            activity?.finish()
        }

        rightButton.setOnClickListener {
            (activity as StartTestActivity).moveNextProblem()
        }

        leftButton.setOnClickListener {
            (activity as StartTestActivity).movePrevProblem()
        }

        submissionButton.setOnClickListener {
            val intent = Intent(activity, TestResultActivity::class.java)
            startActivity(intent)
        }
    }
}