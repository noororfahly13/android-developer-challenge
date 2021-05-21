package com.example.android_developer_challenge.domain

import com.example.entity.database.GithubUserDao
import com.example.entity.entities.GithubUser
import com.example.network.resources.NetworkBoundResource
import com.example.network.services.GithubUserService
import com.example.network.vo.Resource
import com.example.network.vo.SearchGithubUsersResponse
import com.example.network.vo.api.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubUserDomain constructor(
    val githubUserService: GithubUserService,
    val githubUserDao: GithubUserDao
) {

    fun searchGithubUsers(): Flow<Resource<ArrayList<GithubUser>>> {
        return object :
            NetworkBoundResource<ArrayList<GithubUser>, SearchGithubUsersResponse>() {

            var result: SearchGithubUsersResponse =
                SearchGithubUsersResponse(0, true, arrayListOf())

            override fun onFetchFailed() {

            }

            override suspend fun saveCallResult(item: SearchGithubUsersResponse) {
                result = item
                githubUserDao.setGithubUsers(item.items)
            }

            override fun shouldFetch(data: ArrayList<GithubUser>?) = true

            override fun isLastPage(item: SearchGithubUsersResponse): Boolean {
                return item.incomplete_results
            }

            override suspend fun loadFromDb(): ArrayList<GithubUser> {
                return ArrayList(githubUserDao.getGithubUsers())
            }

            override suspend fun createCall(): Flow<ApiResponse<SearchGithubUsersResponse>?> {
                return flow {
                    emit(githubUserService.searchGithubUsers())
                }
            }

        }.asFlowData()
    }

}