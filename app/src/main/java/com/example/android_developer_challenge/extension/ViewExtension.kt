package com.example.android_developer_challenge.extension

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.android_developer_challenge.util.DebounceOnClickListener

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.toggleVisibility(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.toggleEnability(condition: Boolean) {
    isEnabled = condition
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun View.showKeyboard() {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun ImageView.tint(color: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) {
    setColorFilter(ContextCompat.getColor(context!!, color), mode)
}

fun TextView.textColor(color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}

fun View.disable() {
    alpha = 0.7f
    isEnabled = false
}

fun View.enable() {
    alpha = 1f
    isEnabled = true
}

fun View.setOnClickListener(debounceInterval: Long, listenerBlock: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(debounceInterval, listenerBlock))