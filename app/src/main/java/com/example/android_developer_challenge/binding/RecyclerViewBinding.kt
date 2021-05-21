package com.example.android_developer_challenge.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.BaseAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    view.adapter = baseAdapter
}

@BindingAdapter("layoutManager")
fun bindLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

//@BindingAdapter("adapterMovieList")
//fun bindAdapterMovieList(view: RecyclerView, movies: Resource<List<Movie>?>?) {
//    movies?.data?.let {
//        if (it.isNotEmpty())
//            (view.adapter as? MovieAdapter)?.addMovieList(it)
//    }
//}
