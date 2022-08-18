package com.example.swmandroid.data.network

import com.example.swmandroid.model.problem.ProblemResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProblemApiService {

    @GET("/getProblemByTechStack/{techStack}")
    suspend fun getProblemByTechStack(@Path("techStack") techStack : String,) : Response<List<ProblemResponseItem>>
}