package com.example.swmandroid.ui.test.problem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentTestProblemBinding
import com.example.swmandroid.ui.test.TestViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestProblemFragment : BaseFragment<FragmentTestProblemBinding>() {

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

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTestProblemBinding {
        return FragmentTestProblemBinding.inflate(inflater, container, false)
    }

    private val viewModel by stateViewModel<TestViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        viewModel.isFavoriteTestProblem.observe(viewLifecycleOwner, Observer {
            when(it){
                true -> favoriteButton.setImageResource(R.drawable.selected_favorite)
                false -> favoriteButton.setImageResource(R.drawable.unselected_favorite)
            }
        })

        favoriteButton.setOnClickListener {
            viewModel.setFavoriteTestProblem()
        }

        viewModel.getProblem()

        arguments?.let {
            val problemNumber = it.getInt(KEY_PROBLEM_NUMBER)
            val problem = viewModel.problem.value?.get(problemNumber)


        }
    }

}