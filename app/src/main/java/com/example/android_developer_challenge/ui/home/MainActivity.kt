package com.example.android_developer_challenge.ui.home

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.base.BaseActivity
import com.example.android_developer_challenge.databinding.ActivityMainBinding
import com.example.android_developer_challenge.ui.home.adapter.GithubUserAdapter
import com.example.android_developer_challenge.ui.home.viewholder.GithubUserViewHolder
import com.example.android_developer_challenge.ui.home.viewmodel.MainViewModel
import com.example.android_developer_challenge.util.FireIntent
import com.example.entity.entities.GithubUser
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>(), GithubUserViewHolder.Delegate {

    private val viewModel by viewModel<MainViewModel>()

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun getActivityViewModel(): MainViewModel? {
        return viewModel
    }

    override fun initializeUI() {
        super.initializeUI()

        binding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = GithubUserAdapter(this@MainActivity)
        }

        viewModel.searchGithubUsers("n")
    }

    override fun onItemClick(user: GithubUser, view: View) {
        FireIntent.fireDetailsActivity(this, user.login ?: "")
    }
}
