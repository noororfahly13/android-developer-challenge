package com.example.android_developer_challenge.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.android_developer_challenge.extension.hideKeyboard
import com.example.android_developer_challenge.util.FireIntent

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    private var mViewModel: T? = null

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getActivityViewModel(): T?


    /**
     * @param resId layout resource id
     * @return Data binding
     */
    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }


    /**
     * Override to initialize activity layout
     *
     */
    open fun initializeUI() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
        initializeUI()
    }

    private fun setUp() {
        mViewModel = mViewModel?.let { it } ?: getActivityViewModel()
    }


    /**
     *close everything and reopen the splash screen
     */
    fun restartApp() {
        FireIntent.fireMainActivity(this)
    }

    /**
     * this method sets the touch listener for views in the layout to make the keyboard closes when touching outside the edit text
     */
    open fun setupUIForKeyboardBehavior(view: View) { // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, _ ->
                v?.hideKeyboard()
                false
            }
        }
        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until (view).childCount) {
                val innerView: View = (view).getChildAt(i)
                setupUIForKeyboardBehavior(innerView)
            }
        }
    }
}
