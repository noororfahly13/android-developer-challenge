package com.example.network.vo

import com.example.entity.entities.GithubUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchGithubUsersResponse(
    @Json(name = "total_count")
    val total_count: Int,
    @Json(name = "incomplete_results")
    val incomplete_results: Boolean,
    @Json(name = "items")
    val items: MutableList<GithubUser>
)