package com.example.swmandroid.data.repository.community

import com.example.swmandroid.data.network.CommunityApiService
import com.example.swmandroid.data.repository.BaseRepo
import com.example.swmandroid.model.community.delete.DeleteItemInfo
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
import retrofit2.Response

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

    suspend fun getSearchJobPosting(techStack: String, keyword: String, page: Int, size: Int, sort: String): Resource<JobPostingResponse> {
        return safeApiCall { communityApiService.getSearchPosting(techStack, keyword, page, size, sort) }
    }

    suspend fun getSearchJobReview(techStack: String, keyword: String, page: Int, size: Int, sort: String): Resource<JobReviewResponse> {
        return safeApiCall { communityApiService.getSearchReview(techStack, keyword, page, size, sort) }
    }

    suspend fun getSearchStudy(techStack: String, keyword: String, page: Int, size: Int, sort: String): Resource<StudyResponse> {
        return safeApiCall { communityApiService.getSearchStudy(techStack, keyword, page, size, sort) }
    }

    suspend fun getSearchQna(techStack: String, keyword: String, page: Int, size: Int, sort: String): Resource<QuestionResponse> {
        return safeApiCall { communityApiService.getSearchQna(techStack, keyword, page, size, sort) }
    }

    suspend fun deleteJobPostingPost(deleteItemInfo: DeleteItemInfo): Response<ResponseBody> {
        return communityApiService.deleteJobPostingPost(deleteItemInfo)
    }

    suspend fun deleteJobReviewPost(deleteItemInfo: DeleteItemInfo): Response<ResponseBody> {
        return communityApiService.deleteJobReviewPost(deleteItemInfo)
    }

    suspend fun deleteStudyPost(deleteItemInfo: DeleteItemInfo): Response<ResponseBody> {
        return communityApiService.deleteStudyPost(deleteItemInfo)
    }

    suspend fun deleteQnaPost(deleteItemInfo: DeleteItemInfo): Response<ResponseBody> {
        return communityApiService.deleteQnaPost(deleteItemInfo)
    }

}