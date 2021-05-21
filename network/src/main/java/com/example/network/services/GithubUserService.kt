package com.example.network.services

import com.example.entity.entities.GithubUser
import com.example.network.constants.ConnectionEndPoint
import com.example.network.vo.SearchGithubUsersResponse
import com.example.network.vo.api.ApiResponse
import retrofit2.http.GET

interface GithubUserService {
    @GET(ConnectionEndPoint.SEARCH)
    suspend fun searchGithubUsers(): ApiResponse<SearchGithubUsersResponse>

    @GET(ConnectionEndPoint.DETAILS)
    suspend fun getGithubUserDetails(): ApiResponse<GithubUser>
}