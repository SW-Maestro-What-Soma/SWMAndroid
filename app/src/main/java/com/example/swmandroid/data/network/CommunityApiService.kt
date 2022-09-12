package com.example.swmandroid.data.network

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
    suspend fun postStudy(@Body studyContents : StudyItem ) : Response<ResponseBody>

    @POST("/writeQna")
    suspend fun postQuestion(@Body qnaContents : QuestionItem ) : Response<ResponseBody>

    @GET("/searchQna/{keyword}")
    suspend fun getSearchQna(@Path("keyword") keyword : String, @Query("page") page : Int, @Query("size") size:Int, @Query("sort") sort: String) : Response<QuestionResponse>
}