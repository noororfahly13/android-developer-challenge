package com.example.android_developer_challenge.binding

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.example.android_developer_challenge.extension.gone
import com.example.android_developer_challenge.extension.visible
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource

@BindingAdapter("isEmptyStateVisible")
fun bindEmptyStateVisible(
    view: LinearLayout,
    users: Resource<List<GithubUser>?>?
) {
    users?.data?.let {
        if (it.isNotEmpty()) {
            view.gone()
            return
        } else {
            view.visible()
            return
        }
    }
    view.visible()
}