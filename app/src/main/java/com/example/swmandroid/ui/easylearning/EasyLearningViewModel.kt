package com.example.swmandroid.ui.easylearning

import androidx.lifecycle.*
import com.example.swmandroid.data.repository.problem.ProblemRepository
import com.example.swmandroid.model.problem.ProblemResponseItem
import com.example.swmandroid.util.Resource
import kotlinx.coroutines.launch

class EasyLearningViewModel(
    private val handle: SavedStateHandle,
    private val problemRepository: ProblemRepository,
) : ViewModel() {

    companion object {
        const val FAVORITE_LEARNING_PROBLEM = "favorite_learning_problem"
    }

    private val _problem = MutableLiveData<Resource<List<ProblemResponseItem>>>()
    val problem: LiveData<Resource<List<ProblemResponseItem>>> = _problem

    private var favoriteLearningProblem = handle.get<Boolean>(FAVORITE_LEARNING_PROBLEM) ?: false
        set(value) {
            handle[FAVORITE_LEARNING_PROBLEM] = value
            field = value
        }

    private val _isFavoriteLearningProblem: MutableLiveData<Boolean> = handle.getLiveData(FAVORITE_LEARNING_PROBLEM, false)
    val isFavoriteLearningProblem: LiveData<Boolean> = _isFavoriteLearningProblem

    fun setFavoriteLearningProblem() {
        //TODO API 연결해서 서버에 찜한 문제 저장해야함
        favoriteLearningProblem = !favoriteLearningProblem
    }

    fun getProblemByTechStack(techStack: String) = viewModelScope.launch {
        _problem.postValue(Resource.Loading())

        _problem.postValue(problemRepository.getProblemByTechStack(techStack))
    }

}