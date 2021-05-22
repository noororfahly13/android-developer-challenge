package com.example.android_developer_challenge.ui.home.adapter

import android.view.View
import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.ui.home.viewholder.GithubUserViewHolder
import com.example.entity.entities.GithubUser
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class GithubUserAdapter(private val delegate: GithubUserViewHolder.Delegate) : BaseAdapter() {

    init {
        addSection(arrayListOf<GithubUser>())
    }

    fun addUserList(users: List<GithubUser>) {
        sections().first().run {
            clear()
            addAll(users)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.github_user_layout

    override fun viewHolder(layout: Int, view: View) = GithubUserViewHolder(view, delegate)

}