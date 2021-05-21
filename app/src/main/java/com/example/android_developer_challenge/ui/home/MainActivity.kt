package com.example.android_developer_challenge.ui.home

import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.base.BaseActivity
import com.example.android_developer_challenge.databinding.ActivityMainBinding
import com.example.android_developer_challenge.ui.home.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    private val viewModel by viewModel<MainViewModel>()

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun getActivityViewModel(): MainViewModel? {
        return viewModel
    }

    override fun initializeUI() {
        super.initializeUI()

        viewModel.searchGithubUsers()
    }
}
