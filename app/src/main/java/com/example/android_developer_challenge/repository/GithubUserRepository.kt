package com.example.android_developer_challenge.repository

import com.example.android_developer_challenge.domain.GithubUserDomain
import com.example.entity.database.GithubUserDao
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource
import kotlinx.coroutines.flow.Flow

class GithubUserRepository constructor(
    val githubUserDao: GithubUserDao,
    private val githubUserDomain: GithubUserDomain
) {

    fun searchGithubUsers(): Flow<Resource<ArrayList<GithubUser>>> {
        return githubUserDomain.searchGithubUsers()
    }
}