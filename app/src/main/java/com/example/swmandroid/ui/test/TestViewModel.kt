package com.example.swmandroid.ui.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.swmandroid.model.problem.Problem

class TestViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val _problem = MutableLiveData<ArrayList<Problem>>()
    val problem: LiveData<ArrayList<Problem>> = _problem

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
        //TODO API 연결해서 문제 가져와야함
        _problem.value = arrayListOf(
            Problem("문제제목1", "문제내용1"),
            Problem("문제제목2", "문제내용2"),
            Problem("문제제목3", "문제내용3"),
            Problem("문제제목4", "문제내용4"),
            Problem("문제제목5", "문제내용5"),
            Problem("문제제목6", "문제내용6")
        )
    }

}