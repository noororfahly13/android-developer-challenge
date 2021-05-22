package com.example.entity.database

import androidx.room.*
import com.example.entity.entities.GithubUser

@Dao
abstract class GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(githubUser: GithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUsers(githubUsers: List<GithubUser>)

    @Update
    abstract suspend fun updateUser(githubUser: GithubUser)

    @Query("SELECT * FROM GithubUser")
    abstract suspend fun getGithubUsers(): List<GithubUser>?

    @Query("SELECT * FROM GithubUser WHERE login LIKE '%' || :search || '%' OR name LIKE '%' || :search || '%'")
    abstract suspend fun searchGithubUsers(search: String): List<GithubUser>?

    @Query("SELECT * FROM GithubUser WHERE login = :login  LIMIT 1")
    abstract suspend fun fetchGithubUser(login: String): GithubUser?

    @Query("DELETE from GithubUser")
    abstract suspend fun deleteGithubUsers()

    @Query("DELETE FROM GithubUser WHERE id = :id")
    abstract fun delete(id: Long): Int

}