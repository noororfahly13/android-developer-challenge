package com.example.android_developer_challenge.di

import com.example.android_developer_challenge.ui.home.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
