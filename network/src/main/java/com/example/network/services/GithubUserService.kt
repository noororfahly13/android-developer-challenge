package com.example.network.services

import com.example.entity.entities.GithubUser
import com.example.network.constants.ConnectionEndPoint
import com.example.network.vo.SearchGithubUsersResponse
import com.example.network.vo.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserService {
    @GET(ConnectionEndPoint.SEARCH)
    suspend fun searchGithubUsers(@Query("q") query: String): ApiResponse<SearchGithubUsersResponse>

    @GET(ConnectionEndPoint.DETAILS)
    suspend fun getGithubUserDetails(@Path("login") login: String): ApiResponse<GithubUser>
}