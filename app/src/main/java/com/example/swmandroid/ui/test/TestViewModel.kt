package com.example.swmandroid.ui.test

import androidx.lifecycle.*
import com.example.swmandroid.data.repository.problem.ProblemRepository
import com.example.swmandroid.model.problem.ProblemResponseItem
import com.example.swmandroid.util.Resource
import kotlinx.coroutines.launch

class TestViewModel(
    private val handle: SavedStateHandle,
    private val problemRepository: ProblemRepository,
) : ViewModel() {

    companion object {
        const val FAVORITE_TEST_PROBLEM = "favorite_test_problem"
    }

    private val _problem = MutableLiveData<Resource<List<ProblemResponseItem>>>()
    val problem: LiveData<Resource<List<ProblemResponseItem>>> = _problem

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

    fun getProblemByTechStack(techStack: String) = viewModelScope.launch {
        _problem.postValue(Resource.Loading())

        _problem.postValue(problemRepository.getProblemByTechStack(techStack))
    }

}