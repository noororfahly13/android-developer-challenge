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

    fun searchGithubUsers(query: String): Flow<Resource<ArrayList<GithubUser>>> {
        return object :
            NetworkBoundResource<ArrayList<GithubUser>, SearchGithubUsersResponse>() {

            var result: SearchGithubUsersResponse =
                SearchGithubUsersResponse(0, true, arrayListOf())

            override fun onFetchFailed() {

            }

            override suspend fun saveCallResult(item: SearchGithubUsersResponse) {
                result = item
                // cache network call results locally
                githubUserDao.insertUsers(item.items)
            }

            override fun shouldFetch(data: ArrayList<GithubUser>?) =
                true // always try to fetch from network

            override fun isLastPage(item: SearchGithubUsersResponse): Boolean {
                return item.incomplete_results
            }

            override suspend fun loadFromDb(): ArrayList<GithubUser> {
                return ArrayList(githubUserDao.searchGithubUsers(query))
            }

            override suspend fun createCall(): Flow<ApiResponse<SearchGithubUsersResponse>?> {
                return flow {
                    emit(githubUserService.searchGithubUsers(query))
                }
            }

        }.asFlowData()
    }

    fun fetchGithubUser(login: String): Flow<Resource<GithubUser>> {
        return object :
            NetworkBoundResource<GithubUser, GithubUser>() {

            var result: GithubUser = GithubUser(-1, "", "", "", "", "", "", "", 0, 0, 0)

            override fun onFetchFailed() {

            }

            override suspend fun saveCallResult(item: GithubUser) {
                result = item
                // cache network call results locally
                githubUserDao.insertUser(item)
            }

            override fun shouldFetch(data: GithubUser?) = true // always try to fetch from network

            override fun isLastPage(item: GithubUser): Boolean {
                return true
            }

            override suspend fun loadFromDb(): GithubUser {
                return githubUserDao.fetchGithubUser(login) ?: GithubUser(
                    -1,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
                    0,
                    0
                )
            }

            override suspend fun createCall(): Flow<ApiResponse<GithubUser>?> {
                return flow {
                    emit(githubUserService.getGithubUserDetails(login))
                }
            }

        }.asFlowData()
    }

}