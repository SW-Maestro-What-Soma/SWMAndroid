package com.example.swmandroid.data.repository.problem

import com.example.swmandroid.data.network.ProblemApiService
import com.example.swmandroid.data.repository.BaseRepo
import com.example.swmandroid.model.problem.ProblemResponseItem
import com.example.swmandroid.util.Resource

class ProblemRepository(private val problemApiService: ProblemApiService) : BaseRepo() {

    suspend fun getProblemByTechStack(techStack: String): Resource<List<ProblemResponseItem>> {
        return safeApiCall { problemApiService.getProblemByTechStack(techStack) }
    }

}