package com.example.swmandroid.ui.easylearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swmandroid.model.problem.Problem

class EasyLearningViewModel : ViewModel() {

    private val _problem = MutableLiveData<ArrayList<Problem>>()
    val problem : LiveData<ArrayList<Problem>> = _problem

    fun getProblem(){
        //TODO API 연결해서 문제 가져와야함
        _problem.value = arrayListOf(
            Problem("문제제목1","문제내용1"),
            Problem("문제제목2","문제내용2"),
            Problem("문제제목3","문제내용3"),
            Problem("문제제목4","문제내용4"),
            Problem("문제제목5","문제내용5"),
            Problem("문제제목6","문제내용6")
        )
    }

}