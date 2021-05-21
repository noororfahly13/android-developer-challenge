package com.example.entity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entity.entities.GithubUser


@Database(
    entities = [
        GithubUser::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao
}