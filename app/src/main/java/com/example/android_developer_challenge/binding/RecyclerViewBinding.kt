package com.example.android_developer_challenge.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_developer_challenge.ui.home.adapter.GithubUserAdapter
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource
import com.skydoves.baserecyclerviewadapter.BaseAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    view.adapter = baseAdapter
}

@BindingAdapter("layoutManager")
fun bindLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

@BindingAdapter("adapterUserList")
fun bindAdapterUserList(view: RecyclerView, users: Resource<List<GithubUser>?>?) {
    users?.data?.let {
        if (it.isNotEmpty())
            (view.adapter as? GithubUserAdapter)?.addUserList(it)
    }
}
