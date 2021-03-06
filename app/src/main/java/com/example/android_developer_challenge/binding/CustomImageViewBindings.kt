package com.example.android_developer_challenge.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun bindImage(view: ImageView, path: String?) {
    path?.let {
        Glide.with(view.context).load(path)
            .into(view)
    }
}

@BindingAdapter("drawable")
fun bindDrawable(view: ImageView, drawable: Drawable?) {
    drawable?.let {
        view.setImageDrawable(drawable)
    }
}