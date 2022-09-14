package com.example.swmandroid.data.network

import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.model.community.jobposting.JobPostingResponse
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.model.community.jobreview.JobReviewResponse
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.model.community.question.QuestionResponse
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.model.community.study.StudyResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CommunityApiService {

    @GET("/jobPostingList/{tech_stack}")
    suspend fun getJobPostingList(@Path("tech_stack") tech_stack: String, @Query("page") page: Int, @Query("size") size: Int, @Query("sort") sort: String): Response<JobPostingResponse>

    @GET("/jobReviewList/{tech_stack}")
    suspend fun getJobReviewList(@Path("tech_stack") tech_stack: String, @Query("page") page: Int, @Query("size") size: Int, @Query("sort") sort: String): Response<JobReviewResponse>

    @GET("/studyList/{tech_stack}")
    suspend fun getStudyList(@Path("tech_stack") tech_stack: String, @Query("page") page: Int, @Query("size") size: Int, @Query("sort") sort: String): Response<StudyResponse>

    @GET("/qnaBoardList/{tech_stack}")
    suspend fun getQuestionList(@Path("tech_stack") tech_stack: String, @Query("page") page: Int, @Query("size") size: Int, @Query("sort") sort: String): Response<QuestionResponse>

    @POST("/writeJobPosting")
    suspend fun postJobPosting(@Body jobPostingContents: JobPostingItem): Response<ResponseBody>

    @POST("/writeJobReview")
    suspend fun postJobReview(@Body jobReviewContents: JobReviewItem): Response<ResponseBody>

    @POST("/writeStudy")
    suspend fun postStudy(@Body studyContents: StudyItem): Response<ResponseBody>

    @POST("/writeQna")
    suspend fun postQuestion(@Body qnaContents: QuestionItem): Response<ResponseBody>

    @GET("/searchPosting/{tech_stack}/{keyword}")
    suspend fun getSearchPosting(
        @Path("tech_stack") techStack: String,
        @Path("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Response<JobPostingResponse>

    @GET("/searchReview/{tech_stack}/{keyword}")
    suspend fun getSearchReview(
        @Path("tech_stack") techStack: String,
        @Path("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Response<JobReviewResponse>

    @GET("/searchStudy/{tech_stack}/{keyword}")
    suspend fun getSearchStudy(
        @Path("tech_stack") techStack: String,
        @Path("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Response<StudyResponse>

    @GET("/searchQna/{tech_stack}/{keyword}")
    suspend fun getSearchQna(
        @Path("tech_stack") techStack: String,
        @Path("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Response<QuestionResponse>

    @POST("/deleteJobPosting")
    suspend fun deleteJobPostingPost(@Body deleteItemInfo: DeleteItemInfo): Response<ResponseBody>

    @POST("/deleteJobReview")
    suspend fun deleteJobReviewPost(@Body deleteItemInfo: DeleteItemInfo): Response<ResponseBody>

    @POST("/deleteStudy")
    suspend fun deleteStudyPost(@Body deleteItemInfo: DeleteItemInfo): Response<ResponseBody>

    @POST("/deleteQna")
    suspend fun deleteQnaPost(@Body deleteItemInfo: DeleteItemInfo): Response<ResponseBody>

    @GET("jobPostingContent/{id}")
    suspend fun getJobPosting(@Path("id") id: Int): Response<JobPostingItem>

    @GET("jobReviewContent/{id}")
    suspend fun getJobReview(@Path("id") id: Int): Response<JobReviewItem>

    @GET("studyContent/{id}")
    suspend fun getStudy(@Path("id") id: Int): Response<StudyItem>

    @GET("qnaContent/{id}")
    suspend fun getQna(@Path("id") id: Int): Response<QuestionItem>

}