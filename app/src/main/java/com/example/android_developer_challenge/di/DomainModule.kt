package com.example.android_developer_challenge.di

import com.example.android_developer_challenge.domain.GithubUserDomain
import org.koin.dsl.module


val domainModule = module {
    single { GithubUserDomain(get(), get()) }
}
