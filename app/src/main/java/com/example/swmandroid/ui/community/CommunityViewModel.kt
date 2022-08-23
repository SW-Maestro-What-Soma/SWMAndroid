package com.example.swmandroid.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swmandroid.data.entity.RecentSearchEntity
import com.example.swmandroid.data.repository.community.CommunityRepository
import com.example.swmandroid.data.repository.community.RecentSearchRepository
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyResponse
import com.example.swmandroid.util.Resource
import kotlinx.coroutines.launch

class CommunityViewModel(
    private val recentSearchRepository: RecentSearchRepository,
    private val communityRepository: CommunityRepository,
) : ViewModel() {

    private val _recentSearchLiveData = MutableLiveData<List<RecentSearchEntity>>()
    val recentSearchLiveData: LiveData<List<RecentSearchEntity>> = _recentSearchLiveData
    private var recentSearchData = mutableListOf<RecentSearchEntity>()

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _techStack = MutableLiveData<String>()
    val techStack: LiveData<String> = _techStack

    private val _jobPostingList = MutableLiveData<Resource<JobPostingResponse>>()
    val jobPostingList: LiveData<Resource<JobPostingResponse>> = _jobPostingList

    private val _jobReviewList = MutableLiveData<Resource<JobReviewResponse>>()
    val jobReviewList: LiveData<Resource<JobReviewResponse>> = _jobReviewList

    private val _studyList = MutableLiveData<Resource<StudyResponse>>()
    val studyList: LiveData<Resource<StudyResponse>> = _studyList

    private val _questionList = MutableLiveData<Resource<QuestionResponse>>()
    val questionList: LiveData<Resource<QuestionResponse>> = _questionList

    fun addRecentSearchData(recentSearchEntity: RecentSearchEntity) = viewModelScope.launch {
        recentSearchRepository.insertRecentSearch(recentSearchEntity)

        recentSearchData.add(recentSearchEntity)
        _recentSearchLiveData.value = recentSearchData
    }

    fun removeRecentSearchData(recentSearchEntity: RecentSearchEntity) = viewModelScope.launch {
        recentSearchRepository.removeRecentSearch(recentSearchEntity)

        recentSearchData.remove(recentSearchEntity)
        _recentSearchLiveData.value = recentSearchData
    }

    fun removeAllRecentSearchData(category: String, techStack: String) = viewModelScope.launch {
        recentSearchRepository.clearRecentSearch(category, techStack)

        recentSearchData = recentSearchData.filter { it.category != category && it.techStack != techStack }.toMutableList()
        _recentSearchLiveData.value = recentSearchData
    }

    fun getAllRecentSearchData(category: String, techStack: String) = viewModelScope.launch {
        val dataList = recentSearchRepository.getAllRecentSearch(category, techStack)

        recentSearchData.clear()
        recentSearchData = dataList.toMutableList()
        _recentSearchLiveData.value = recentSearchData
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun setTechStack(techStack: String) {
        _techStack.value = techStack
    }

    fun getJobPostingList(techStack: String, page: Int, size: Int) = viewModelScope.launch {
        _jobPostingList.postValue(Resource.Loading())

        _jobPostingList.postValue(communityRepository.getJobPostingList(techStack, page, size))
    }

    fun getJobReviewList(techStack: String, page: Int, size: Int) = viewModelScope.launch {
        _jobReviewList.postValue(Resource.Loading())

        _jobReviewList.postValue(communityRepository.getJobReviewList(techStack, page, size))
    }

    fun getStudyList(techStack: String, page: Int, size: Int) = viewModelScope.launch {
        _studyList.postValue(Resource.Loading())

        _studyList.postValue(communityRepository.getStudyList(techStack, page, size))
    }

    fun getQuestionList(techStack: String, page: Int, size: Int) = viewModelScope.launch {
        _questionList.postValue(Resource.Loading())

        _questionList.postValue(communityRepository.getQuestionList(techStack, page, size))
    }

}