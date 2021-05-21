package com.example.android_developer_challenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.extension.observeLiveData
import com.example.network.constants.ConnectionErrorType

abstract class BaseFragment<T : BaseViewModel, V : ViewDataBinding> : Fragment() {

    private var mViewModel: T? = null
    private var mRootView: View? = null
    protected lateinit var binding: V

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getFragmentViewModel(): T?


    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    /**
     * Override to initialize activity layout
     *
     */
    open fun initializeUI() {}

    protected inline fun <reified B : ViewDataBinding> bindingView(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): B = DataBindingUtil.inflate(inflater, resId, container, false)

    /**
     * Override to customize error messages
     *
     */
    open fun apiErrorMessage(code: Int, message: String?) {
        val msg = message?.let { it } ?: getString(R.string.error_connection_error)
        showToast(msg)
    }

    private fun apiErrorHandler(code: Int, message: String?) {
        when (code) {
            ConnectionErrorType.NO_INTERNET -> {
                showToast(getString(R.string.error_no_internet))
            }
            ConnectionErrorType.SESSION_EXPIRED -> {
                showToast(getString(R.string.error_session_expired))
            }
            else -> {
                apiErrorMessage(code, message)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = binding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        initializeUI()
    }

    private fun showToast(msg: String) {
        activity?.let { Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show() }
    }

    private fun setUp() {
        mViewModel = mViewModel?.let { it } ?: getFragmentViewModel()

        mViewModel?.let { it ->
            observeLiveData(it.apiErrorLiveData) {
                apiErrorHandler(it.first, it.second)
            }
        }

    }

}