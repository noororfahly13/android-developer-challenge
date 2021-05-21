package com.example.android_developer_challenge.di

import com.example.android_developer_challenge.repository.GithubUserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { GithubUserRepository(get(), get()) }
}
