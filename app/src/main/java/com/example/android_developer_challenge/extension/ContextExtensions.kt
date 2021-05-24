package com.example.android_developer_challenge.extension

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/** shows a toast message. */
fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

/**
 * create native alert dialog
 */
fun Context.showDialog(
    title: String = "",
    message: String = "",
    positiveBtn: String = getString(android.R.string.yes),
    positiveAction: () -> Unit
) {
    val builder = androidx.appcompat.app.AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)

    builder.setPositiveButton(positiveBtn) { _, _ ->
        positiveAction()
    }

    builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
        dialog.dismiss()
    }
    builder.show()
}

fun Context.getScreenWidth(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

fun Context.getScreenHeight(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}