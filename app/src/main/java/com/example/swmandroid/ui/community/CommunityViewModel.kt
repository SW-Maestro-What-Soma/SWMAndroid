package com.example.swmandroid.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swmandroid.data.entity.RecentSearchEntity
import com.example.swmandroid.data.repository.community.CommunityRepository
import com.example.swmandroid.data.repository.community.RecentSearchRepository
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.model.community.study.StudyResponse
import com.example.swmandroid.util.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class CommunityViewModel(
    private val recentSearchRepository: RecentSearchRepository,
    private val communityRepository: CommunityRepository,
) : ViewModel() {

    private val _recentSearchLiveData = MutableLiveData<List<RecentSearchEntity>>()
    val recentSearchLiveData: LiveData<List<RecentSearchEntity>> = _recentSearchLiveData
    private var recentSearchData = mutableListOf<RecentSearchEntity>()

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

    private val _fullJobPostingList = MutableLiveData<Resource<JobPostingResponse>>()
    val fullJobPostingList: LiveData<Resource<JobPostingResponse>> = _fullJobPostingList

    private val _fullJobReviewList = MutableLiveData<Resource<JobReviewResponse>>()
    val fullJobReviewList: LiveData<Resource<JobReviewResponse>> = _fullJobReviewList

    private val _fullStudyList = MutableLiveData<Resource<StudyResponse>>()
    val fullStudyList: LiveData<Resource<StudyResponse>> = _fullStudyList

    private val _fullQuestionList = MutableLiveData<Resource<QuestionResponse>>()
    val fullQuestionList: LiveData<Resource<QuestionResponse>> = _fullQuestionList

    private val _sort = MutableLiveData("id,DESC")
    val sort: LiveData<String> = _sort

    private val _statusPostJobPosting = MutableLiveData<Resource<ResponseBody>>()
    val statusPostJobPosting: LiveData<Resource<ResponseBody>> = _statusPostJobPosting

    private val _statusPostJobReview = MutableLiveData<Resource<ResponseBody>>()
    val statusPostJobReview: LiveData<Resource<ResponseBody>> = _statusPostJobReview

    private val _statusPostStudy = MutableLiveData<Resource<ResponseBody>>()
    val statusPostStudy : LiveData<Resource<ResponseBody>> = _statusPostStudy

    var categoryData = ""

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
        categoryData = category
    }

    fun setTechStack(techStack: String) {
        _techStack.value = techStack
    }

    fun getJobPostingList(techStack: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _jobPostingList.postValue(Resource.Loading())

        _jobPostingList.postValue(communityRepository.getJobPostingList(techStack, page, size, sort))
    }

    fun getJobReviewList(techStack: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _jobReviewList.postValue(Resource.Loading())

        _jobReviewList.postValue(communityRepository.getJobReviewList(techStack, page, size, sort))
    }

    fun getStudyList(techStack: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _studyList.postValue(Resource.Loading())

        _studyList.postValue(communityRepository.getStudyList(techStack, page, size, sort))
    }

    fun getQuestionList(techStack: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _questionList.postValue(Resource.Loading())

        _questionList.postValue(communityRepository.getQuestionList(techStack, page, size, sort))
    }

    fun getFullJobPostingList() = viewModelScope.launch {
        _fullJobPostingList.postValue(Resource.Loading())

        _fullJobPostingList.postValue(communityRepository.getJobPostingList("ALL", 0, 3, "id,DESC"))
    }

    fun getFullJobReviewList() = viewModelScope.launch {
        _fullJobReviewList.postValue(Resource.Loading())

        _fullJobReviewList.postValue(communityRepository.getJobReviewList("ALL", 0, 3, "id,DESC"))
    }

    fun getFullStudyList() = viewModelScope.launch {
        _fullStudyList.postValue(Resource.Loading())

        _fullStudyList.postValue(communityRepository.getStudyList("ALL", 0, 3, "id,DESC"))
    }

    fun getFullQuestionList() = viewModelScope.launch {
        _fullQuestionList.postValue(Resource.Loading())

        _fullQuestionList.postValue(communityRepository.getQuestionList("ALL", 0, 4, "id,DESC"))
    }

    fun setNewOrder() {
        _sort.value = "id,DESC"
    }

    fun setViewOrder() {
        _sort.value = "viewCount,DESC"
    }

    fun postJobPosting(jobPostingItem: JobPostingItem) = viewModelScope.launch {
        _statusPostJobPosting.postValue(Resource.Loading())

        _statusPostJobPosting.postValue(communityRepository.postJobPosting(jobPostingItem))
    }

    fun postJobReview(jobReviewItem: JobReviewItem) = viewModelScope.launch {
        _statusPostJobReview.postValue(Resource.Loading())

        _statusPostJobReview.postValue(communityRepository.postJobReview(jobReviewItem))
    }

    fun postStudy(studyItem: StudyItem) = viewModelScope.launch {
        _statusPostStudy.postValue(Resource.Loading())

        _statusPostStudy.postValue(communityRepository.postStudy(studyItem))
    }

}