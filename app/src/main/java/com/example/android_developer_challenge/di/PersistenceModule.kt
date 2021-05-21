package com.example.android_developer_challenge.di

import androidx.room.Room
import com.example.entity.database.AppDatabase
import com.squareup.moshi.Moshi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(), AppDatabase::class.java,
            "DB name"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single { get<AppDatabase>().githubUserDao() }

}