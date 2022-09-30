package com.example.swmandroid.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swmandroid.data.repository.community.CommunityRepository
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.model.community.study.StudyResponse
import com.example.swmandroid.model.community.update.UpdateJobPostingItem
import com.example.swmandroid.model.community.update.UpdateJobReviewItem
import com.example.swmandroid.model.community.update.UpdateQnaItem
import com.example.swmandroid.model.community.update.UpdateStudyItem
import com.example.swmandroid.util.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class CommunityViewModel(
    private val communityRepository: CommunityRepository,
) : ViewModel() {

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

    private val _statusPostJobPosting = MutableLiveData<Resource<ResponseBody>>()
    val statusPostJobPosting: LiveData<Resource<ResponseBody>> = _statusPostJobPosting

    private val _statusPostJobReview = MutableLiveData<Resource<ResponseBody>>()
    val statusPostJobReview: LiveData<Resource<ResponseBody>> = _statusPostJobReview

    private val _statusPostStudy = MutableLiveData<Resource<ResponseBody>>()
    val statusPostStudy: LiveData<Resource<ResponseBody>> = _statusPostStudy

    private val _statusPostQuestion = MutableLiveData<Resource<ResponseBody>>()
    val statusPostQuestion: LiveData<Resource<ResponseBody>> = _statusPostQuestion

    private val _statusDeleteJobPostingPost = MutableLiveData<Response<ResponseBody>>()
    val statusDeleteJobPostingPost: LiveData<Response<ResponseBody>> = _statusDeleteJobPostingPost

    private val _statusDeleteJobReviewPost = MutableLiveData<Response<ResponseBody>>()
    val statusDeleteJobReviewPost: LiveData<Response<ResponseBody>> = _statusDeleteJobReviewPost

    private val _statusDeleteStudyPost = MutableLiveData<Response<ResponseBody>>()
    val statusDeleteStudyPost: LiveData<Response<ResponseBody>> = _statusDeleteStudyPost

    private val _statusDeleteQnaPost = MutableLiveData<Response<ResponseBody>>()
    val statusDeleteQnaPost: LiveData<Response<ResponseBody>> = _statusDeleteQnaPost

    private val _jobPosting = MutableLiveData<Resource<JobPostingItem>>()
    val jobPosting: LiveData<Resource<JobPostingItem>> = _jobPosting

    private val _jobReview = MutableLiveData<Resource<JobReviewItem>>()
    val jobReview: LiveData<Resource<JobReviewItem>> = _jobReview

    private val _study = MutableLiveData<Resource<StudyItem>>()
    val study: LiveData<Resource<StudyItem>> = _study

    private val _question = MutableLiveData<Resource<QuestionItem>>()
    val question: LiveData<Resource<QuestionItem>> = _question

    private val _updateJobPostingPost = MutableLiveData<Response<ResponseBody>>()
    val updateJobPostingPost: LiveData<Response<ResponseBody>> = _updateJobPostingPost

    private val _updateJobReviewPost = MutableLiveData<Response<ResponseBody>>()
    val updateJobReviewPost: LiveData<Response<ResponseBody>> = _updateJobReviewPost

    private val _updateStudyPost = MutableLiveData<Response<ResponseBody>>()
    val updateStudyPost: LiveData<Response<ResponseBody>> = _updateStudyPost

    private val _updateQnaPost = MutableLiveData<Response<ResponseBody>>()
    val updateQnaPost: LiveData<Response<ResponseBody>> = _updateQnaPost

    var categoryData = ""
    var sortData = "id,DESC"

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

    fun postQuestion(questionItem: QuestionItem) = viewModelScope.launch {
        _statusPostQuestion.postValue(Resource.Loading())

        _statusPostQuestion.postValue(communityRepository.postQuestion(questionItem))
    }

    fun getSearchJobPosting(techStack: String, keyword: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _jobPostingList.postValue(Resource.Loading())

        _jobPostingList.postValue(communityRepository.getSearchJobPosting(techStack, keyword, page, size, sort))
    }

    fun getSearchJobReview(techStack: String, keyword: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _jobReviewList.postValue(Resource.Loading())

        _jobReviewList.postValue(communityRepository.getSearchJobReview(techStack, keyword, page, size, sort))
    }

    fun getSearchStudy(techStack: String, keyword: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _studyList.postValue(Resource.Loading())

        _studyList.postValue(communityRepository.getSearchStudy(techStack, keyword, page, size, sort))
    }

    fun getSearchQna(techStack: String, keyword: String, page: Int, size: Int, sort: String) = viewModelScope.launch {
        _questionList.postValue(Resource.Loading())

        _questionList.postValue(communityRepository.getSearchQna(techStack, keyword, page, size, sort))
    }

    fun deleteJobPostingPost(deleteItemInfo: DeleteItemInfo) = viewModelScope.launch {
        _statusDeleteJobPostingPost.postValue(communityRepository.deleteJobPostingPost(deleteItemInfo))
    }

    fun deleteJobReviewPost(deleteItemInfo: DeleteItemInfo) = viewModelScope.launch {
        _statusDeleteJobReviewPost.postValue(communityRepository.deleteJobReviewPost(deleteItemInfo))
    }

    fun deleteStudyPost(deleteItemInfo: DeleteItemInfo) = viewModelScope.launch {
        _statusDeleteStudyPost.postValue(communityRepository.deleteStudyPost(deleteItemInfo))
    }

    fun deleteQnaPost(deleteItemInfo: DeleteItemInfo) = viewModelScope.launch {
        _statusDeleteQnaPost.postValue(communityRepository.deleteQnaPost(deleteItemInfo))
    }

    fun getJobPosting(postId: Int) = viewModelScope.launch {
        _jobPosting.postValue(Resource.Loading())

        _jobPosting.postValue(communityRepository.getJobPosting(postId))
    }

    fun getJobReview(postId: Int) = viewModelScope.launch {
        _jobReview.postValue(Resource.Loading())

        _jobReview.postValue(communityRepository.getJobReview(postId))
    }

    fun getStudy(postId: Int) = viewModelScope.launch {
        _study.postValue(Resource.Loading())

        _study.postValue(communityRepository.getStudy(postId))
    }

    fun getQna(postId: Int) = viewModelScope.launch {
        _question.postValue(Resource.Loading())

        _question.postValue(communityRepository.getQna(postId))
    }

    fun updateJobPosting(updateJobPostingItem: UpdateJobPostingItem) = viewModelScope.launch {
        _updateJobPostingPost.postValue(communityRepository.updateJobPosting(updateJobPostingItem))
    }

    fun updateJobReview(updateJobReviewItem: UpdateJobReviewItem) = viewModelScope.launch {
        _updateJobReviewPost.postValue(communityRepository.updateJobReview(updateJobReviewItem))
    }

    fun updateStudy(updateStudyItem: UpdateStudyItem) = viewModelScope.launch {
        _updateStudyPost.postValue(communityRepository.updateStudy(updateStudyItem))
    }

    fun updateQna(updateQnaItem: UpdateQnaItem) = viewModelScope.launch {
        _updateQnaPost.postValue(communityRepository.updateQna(updateQnaItem))
    }

    fun setCategory(category: String) {
        categoryData = category
    }

    fun setOrder(sort: String) {
        sortData = sort
    }

}