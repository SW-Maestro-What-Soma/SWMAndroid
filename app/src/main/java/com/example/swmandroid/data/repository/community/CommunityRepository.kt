package com.example.swmandroid.data.repository.community

import com.example.swmandroid.data.network.CommunityApiService
import com.example.swmandroid.data.repository.BaseRepo
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyResponse
import com.example.swmandroid.util.Resource

class CommunityRepository(private val communityApiService: CommunityApiService) : BaseRepo() {

    suspend fun getJobPostingList(techStack: String, page: Int, size: Int): Resource<JobPostingResponse> {
        return safeApiCall { communityApiService.getJobPostingList(techStack, page, size) }
    }

    suspend fun getJobReviewList(techStack: String, page: Int, size: Int): Resource<JobReviewResponse> {
        return safeApiCall { communityApiService.getJobReviewList(techStack, page, size) }
    }

    suspend fun getStudyList(techStack: String, page: Int, size: Int): Resource<StudyResponse> {
        return safeApiCall { communityApiService.getStudyList(techStack, page, size) }
    }

    suspend fun getQuestionList(techStack: String, page: Int, size: Int): Resource<QuestionResponse> {
        return safeApiCall { communityApiService.getQuestionList(techStack, page, size) }
    }

}