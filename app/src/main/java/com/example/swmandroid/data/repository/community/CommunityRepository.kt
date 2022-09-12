package com.example.swmandroid.data.repository.community

import com.example.swmandroid.data.network.CommunityApiService
import com.example.swmandroid.data.repository.BaseRepo
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.model.community.study.StudyResponse
import com.example.swmandroid.util.Resource
import okhttp3.ResponseBody

class CommunityRepository(private val communityApiService: CommunityApiService) : BaseRepo() {

    suspend fun getJobPostingList(techStack: String, page: Int, size: Int, sort: String): Resource<JobPostingResponse> {
        return safeApiCall { communityApiService.getJobPostingList(techStack, page, size, sort) }
    }

    suspend fun getJobReviewList(techStack: String, page: Int, size: Int, sort: String): Resource<JobReviewResponse> {
        return safeApiCall { communityApiService.getJobReviewList(techStack, page, size, sort) }
    }

    suspend fun getStudyList(techStack: String, page: Int, size: Int, sort: String): Resource<StudyResponse> {
        return safeApiCall { communityApiService.getStudyList(techStack, page, size, sort) }
    }

    suspend fun getQuestionList(techStack: String, page: Int, size: Int, sort: String): Resource<QuestionResponse> {
        return safeApiCall { communityApiService.getQuestionList(techStack, page, size, sort) }
    }

    suspend fun postJobPosting(jobPostingItem: JobPostingItem): Resource<ResponseBody> {
        return safeApiCall { communityApiService.postJobPosting(jobPostingItem) }
    }

    suspend fun postJobReview(jobReviewItem: JobReviewItem): Resource<ResponseBody> {
        return safeApiCall { communityApiService.postJobReview(jobReviewItem) }
    }

    suspend fun postStudy(studyItem: StudyItem): Resource<ResponseBody> {
        return safeApiCall { communityApiService.postStudy(studyItem) }
    }

    suspend fun postQuestion(questionItem: QuestionItem): Resource<ResponseBody> {
        return safeApiCall { communityApiService.postQuestion(questionItem) }
    }

    suspend fun getSearchQna(keyword: String, page: Int, size: Int, sort: String): Resource<QuestionResponse> {
        return safeApiCall { communityApiService.getSearchQna(keyword, page, size, sort) }
    }

}