package com.example.entity.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class GithubUser(
    @PrimaryKey val id: Int,
    val image: String?,
    val name: String?,
    val userName: String?,
    val bio: String?
)