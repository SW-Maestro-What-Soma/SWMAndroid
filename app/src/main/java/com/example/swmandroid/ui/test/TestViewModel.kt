package com.example.swmandroid.ui.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.swmandroid.model.problem.ProblemResponseItem

class TestViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val _problem = MutableLiveData<List<ProblemResponseItem>>()
    val problem: LiveData<List<ProblemResponseItem>> = _problem

    private val FAVORITE_TEST_PROBLEM = "favorite_test_problem"

    private var favoriteTestProblem = handle.get<Boolean>(FAVORITE_TEST_PROBLEM) ?: false
        set(value) {
            handle[FAVORITE_TEST_PROBLEM] = value
            field = value
        }

    private val _isFavoriteTestProblem: MutableLiveData<Boolean> = handle.getLiveData(FAVORITE_TEST_PROBLEM, false)
    val isFavoriteTestProblem: LiveData<Boolean> = _isFavoriteTestProblem

    fun setFavoriteTestProblem() {
        //TODO API 연결해서 찜한 문제 서버에 저장해야함
        favoriteTestProblem = !favoriteTestProblem
    }

    fun getProblem() {

    }

}