package com.example.swmandroid.data.network

import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommunityApiService {

    @GET("/jobPostingList/{tech_category}")
    suspend fun getJobPostingList(@Path("tech_category") tech_category: String, @Query("page") page: Int, @Query("size") size: Int): Response<JobPostingResponse>

    @GET("/jobReviewList/{tech_category}")
    suspend fun getJobReviewList(@Path("tech_category") tech_category: String, @Query("page") page: Int, @Query("size") size: Int): Response<JobReviewResponse>

    @GET("/studyList/{tech_category}")
    suspend fun getStudyList(@Path("tech_category") tech_category: String, @Query("page") page: Int, @Query("size") size: Int): Response<StudyResponse>

    @GET("/qnaBoardList/{tech_stack}")
    suspend fun getQuestionList(@Path("tech_stack") tech_stack: String, @Query("page") page: Int, @Query("size") size: Int): Response<QuestionResponse>

}