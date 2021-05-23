package com.example.android_developer_challenge.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android_developer_challenge.extension.gone
import com.example.android_developer_challenge.extension.visible
import com.example.entity.entities.GithubUser
import com.example.network.vo.Resource
import com.example.network.vo.Status

@BindingAdapter("valueText")
fun bindValueText(view: TextView, text: String?) {
    if (text.isNullOrEmpty())
        view.text = "--"
    else
        view.text = text
}

@BindingAdapter("isEmptyListHintVisible", "isInSearchMode")
fun bindIsEmptyListHintVisible(
    view: TextView,
    users: Resource<List<GithubUser>?>?,
    isInSearchMode: Boolean?
) {
    if (isInSearchMode != true && (users == null || users.status == Status.ERROR))
        view.visible()
    else
        view.gone()
}