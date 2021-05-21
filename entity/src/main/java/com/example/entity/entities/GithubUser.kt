package com.example.entity.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class GithubUser(
    @PrimaryKey val id: Int,
    val avatar_url: String?,
    val name: String?,
    val login: String?,
    val bio: String?
)