package com.example.android_developer_challenge.ui.home

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.base.BaseActivity
import com.example.android_developer_challenge.databinding.ActivityMainBinding
import com.example.android_developer_challenge.extension.*
import com.example.android_developer_challenge.ui.home.adapter.GithubUserAdapter
import com.example.android_developer_challenge.ui.home.viewholder.GithubUserViewHolder
import com.example.android_developer_challenge.ui.home.viewmodel.MainViewModel
import com.example.android_developer_challenge.util.FireIntent
import com.example.entity.entities.GithubUser
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    searchBar.searchET.width =
                        getScreenWidth() - 112.toPx() // set the width of the edit text to screen width minus the horizontal margin and padding
                }, 10)
            }
        }
        binding.searchBar.trailingIconIV.setOnClickListener {
            viewModel.toggleSearchMode()
        }

        observeLiveData(viewModel.isInSearchMode) { isInSearchMode ->
            Looper.myLooper()?.let {
                if (isInSearchMode) {
                    Handler(it).postDelayed({
                        binding.searchBar.searchET.requestFocus()
                        binding.searchBar.searchET.showKeyboard()
                    }, 50)
                } else {
                    Handler(it).postDelayed({
                        binding.root.clearFocus()
                        binding.searchBar.searchET.text.clear()
                        binding.searchBar.searchET.hideKeyboard()
                    }, 50)
                }
            }
        }

        binding.searchBar.searchET.textChanges()
            .filterNot { it.isNullOrBlank() }
            .debounce(300)
            .onEach {
                viewModel.searchGithubUsers(it.toString())
            }
            .launchIn(lifecycleScope)
    }

    override fun onItemClick(user: GithubUser, view: View) {
        FireIntent.fireDetailsActivity(this, user.login ?: "")
    }
}
