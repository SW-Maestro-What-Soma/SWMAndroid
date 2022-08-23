package com.example.swmandroid.data.repository.community

import com.example.swmandroid.data.network.CommunityApiService
import com.example.swmandroid.data.repository.BaseRepo
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyResponse
import com.example.swmandroid.util.Resource

class CommunityRepository(private val communityApiService: CommunityApiService) : BaseRepo() {

    suspend fun getJobPostingList(techCategory: String, page: Int, size: Int): Resource<JobPostingResponse> {
        return safeApiCall { communityApiService.getJobPostingList(techCategory, page, size) }
    }

    suspend fun getJobReviewList(techCategory: String, page: Int, size: Int): Resource<JobReviewResponse> {
        return safeApiCall { communityApiService.getJobReviewList(techCategory, page, size) }
    }

    suspend fun getStudyList(techCategory: String, page: Int, size: Int): Resource<StudyResponse> {
        return safeApiCall { communityApiService.getStudyList(techCategory, page, size) }
    }

    suspend fun getQuestionList(techCategory: String, page: Int, size: Int): Resource<QuestionResponse> {
        return safeApiCall { communityApiService.getQuestionList(techCategory, page, size) }
    }

}