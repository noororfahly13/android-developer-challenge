package com.example.android_developer_challenge.binding

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.android_developer_challenge.extension.gone
import com.example.android_developer_challenge.extension.visible
import com.example.network.vo.Status

@BindingAdapter("stateLoader")
fun bindLoaderState(view: ProgressBar, status: Status?) {
    when (status) {
        Status.LOADING -> {
            view.visible()
        }
        else -> {
            view.gone()
        }
    }
}