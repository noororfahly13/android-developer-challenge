package com.example.android_developer_challenge.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("valueText")
fun bindValueText(view: TextView, text: String?) {
    if (text.isNullOrEmpty())
        view.text = "--"
    else
        view.text = text
}