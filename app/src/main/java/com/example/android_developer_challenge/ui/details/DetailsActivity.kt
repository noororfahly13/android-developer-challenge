package com.example.android_developer_challenge.ui.details

import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.base.BaseActivity
import com.example.android_developer_challenge.databinding.ActivityDetailsBinding
import com.example.android_developer_challenge.extension.setOnClickListener
import com.example.android_developer_challenge.ui.details.viewmodel.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : BaseActivity<DetailsViewModel>() {

    companion object {
        const val LOGIN_ARGUMENT = "login_argument"
    }

    private val viewModel by viewModel<DetailsViewModel>()

    private val binding: ActivityDetailsBinding by binding(R.layout.activity_details)

    override fun getActivityViewModel(): DetailsViewModel? {
        return viewModel
    }

    override fun initializeUI() {
        super.initializeUI()

        binding.apply {
            viewModel = this@DetailsActivity.viewModel
            lifecycleOwner = this@DetailsActivity

            backIV.setOnClickListener(1000L) {
                finish()
            }
        }

        intent.getStringExtra(LOGIN_ARGUMENT)?.let {
            viewModel.fetchGithubUser(it)
        }

    }

}
