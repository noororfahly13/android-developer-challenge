package com.example.android_developer_challenge.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android_developer_challenge.R

/**
 *  Contains useful extension functions over the [FragmentManager] to perform the add, replace
 * and remove fragment operations with a single call
 */

internal fun FragmentManager.removeFragment(
    tag: String,
    slideIn: Int = R.anim.slide_in_left,
    slideOut: Int = R.anim.slide_out_right
) {
    this.beginTransaction()
        .disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut)
        .remove(this.findFragmentByTag(tag)!!)
        .commitNow()
}

internal fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.fade_in,
    slideOut: Int = R.anim.fade_out,
    popEnterAnimation: Int = R.anim.fade_in,
    popExitAnimation: Int = R.anim.fade_out
) {
    this.beginTransaction()
        .setCustomAnimations(slideIn, slideOut, popEnterAnimation, popExitAnimation)
        .add(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

internal fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.slide_in_right,
    slideOut: Int = R.anim.slide_out_left
) {
    this.beginTransaction()
        .setCustomAnimations(slideIn, slideOut, slideIn, slideOut)
        .replace(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

internal fun FragmentManager.replaceFragmentWithoutBackStack(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.slide_in_left,
    slideOut: Int = R.anim.slide_out_right
) {
    this.beginTransaction()
        .setCustomAnimations(slideIn, slideOut, slideIn, slideOut)
        .replace(containerViewId, fragment, tag)
        .commit()
}
