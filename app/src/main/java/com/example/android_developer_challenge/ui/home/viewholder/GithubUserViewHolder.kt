package com.example.android_developer_challenge.ui.home.viewholder

import android.view.View
import com.example.android_developer_challenge.binding.bindings
import com.example.android_developer_challenge.databinding.LayoutGithubUserBinding
import com.example.android_developer_challenge.extension.setOnClickListener
import com.example.entity.entities.GithubUser
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class GithubUserViewHolder(
    private val view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(user: GithubUser, view: View)
    }

    lateinit var githubUser: GithubUser

    private val binding by bindings<LayoutGithubUserBinding>(
        view
    )

    override fun bindData(data: Any) {
        if (data is GithubUser) {
            githubUser = data
            binding.login = if (githubUser.login != null) "@" + githubUser.login else ""
            binding.image = githubUser.avatar_url ?: ""
            binding.executePendingBindings()

            view.setOnClickListener(1000L) { delegate.onItemClick(githubUser, itemView.rootView) }
        }
    }

    override fun onClick(v: View?) {

    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }

}