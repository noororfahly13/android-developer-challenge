package com.example.entity.database

import androidx.room.*
import com.example.entity.entities.GithubUser

@Dao
abstract class GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(githubUser: GithubUser)

    @Update
    abstract suspend fun updateUser(githubUser: GithubUser)

    @Query("SELECT * FROM GithubUser")
    abstract suspend fun getGithubUser(): GithubUser?


    @Query("DELETE FROM GithubUser WHERE id = :id")
    abstract fun delete(id: Long): Int
}