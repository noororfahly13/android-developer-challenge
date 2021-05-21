package com.example.network.vo

import com.example.network.vo.Status.*
import java.net.HttpURLConnection.HTTP_OK

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val code: Int = -1,
    val onLastPage: Boolean,
    val rawResponse: String? = null
) {
    companion object {
        fun <T> success(data: T?, onLastPage: Boolean = false): Resource<T> {
            return Resource(SUCCESS, data, null, HTTP_OK, onLastPage)
        }

        fun <T> error(msg: String, data: T?, code: Int, rawResponse: String? = null): Resource<T> {
            return Resource(ERROR, data, msg, code, true, rawResponse)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null, -1, false)
        }
    }

    override fun equals(other: Any?): Boolean {

        if (other is Resource<*>) {
            if (other.status == this.status) {
                return true
            }
        }

        return super.equals(other)
    }

    fun dataIsNull() = data == null
}