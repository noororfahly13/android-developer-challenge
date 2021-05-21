package com.example.android_developer_challenge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.network.vo.Resource
import com.example.network.vo.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

open class BaseViewModel : ViewModel() {

    var apiErrorLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()

    fun <T> handleResponse(resource: Resource<T>) {
        if (resource.status == Status.ERROR) {
            apiErrorLiveData.postValue(Pair(resource.code, resource.message))
        }
    }

    suspend inline fun <T> collect(
        flow: Flow<Resource<T>>,
        crossinline action: suspend (value: Resource<T>) -> Unit
    ) {
        flow.collect(this, action)
    }

}


/**
 *  surround flow collect with base view model error types handling
 *
 * @param baseViewModel reference
 * @param action block of actions
 */
suspend inline fun <T> Flow<Resource<T>>.collect(
    baseViewModel: BaseViewModel,
    crossinline action: suspend (value: Resource<T>) -> Unit
): Unit =
    collect(object : FlowCollector<Resource<T>> {
        override suspend fun emit(value: Resource<T>) {
            baseViewModel.handleResponse(value)
            action(value)
        }
    })