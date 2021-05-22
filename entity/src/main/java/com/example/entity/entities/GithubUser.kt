package com.example.entity.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class GithubUser(
    @PrimaryKey val id: Int,
    val avatar_url: String?,
    val name: String?,
    val login: String?,
    val bio: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    @Json(name = "public_repos")
    val publicRepos: Int?,
    @Json(name = "followers_count")
    val followersCount: Int?,
    @Json(name = "following_count")
    val followingCount: Int?
)