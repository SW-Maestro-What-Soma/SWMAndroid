package com.example.swmandroid.ui.easylearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.swmandroid.model.problem.Problem

class EasyLearningViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val _problem = MutableLiveData<ArrayList<Problem>>()
    val problem: LiveData<ArrayList<Problem>> = _problem

    private val FAVORITE_LEARNING_PROBLEM = "favorite_learning_problem"

    private var favoriteLearningProblem = handle.get<Boolean>(FAVORITE_LEARNING_PROBLEM) ?: false
        set(value) {
            handle[FAVORITE_LEARNING_PROBLEM] = value
            field = value
        }

    private val _isFavoriteLearningProblem : MutableLiveData<Boolean> = handle.getLiveData(FAVORITE_LEARNING_PROBLEM, false)
    val isFavoriteLearningProblem : LiveData<Boolean> = _isFavoriteLearningProblem

    fun setFavoriteLearningProblem() {
        //TODO API 연결해서 서버에 찜한 문제 저장해야함
        favoriteLearningProblem = !favoriteLearningProblem
    }

    fun getProblem() {
        //TODO API 연결해서 문제 가져와야함
        _problem.value = arrayListOf(
            Problem("문제제목1", "문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n문제내용1\n"),
            Problem("문제제목2", "문제내용2"),
            Problem("문제제목3", "문제내용3"),
            Problem("문제제목4", "문제내용4"),
            Problem("문제제목5", "문제내용5"),
            Problem("문제제목6", "문제내용6")
        )
    }

}