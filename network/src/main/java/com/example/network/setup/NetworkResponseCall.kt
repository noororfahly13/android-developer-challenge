package com.example.network.setup

import com.example.network.vo.api.ApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class NetworkResponseCall<S : Any>(
    private val delegate: Call<S>
) : Call<ApiResponse<S>> {

    override fun enqueue(callback: Callback<ApiResponse<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {

                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(ApiResponse.create(response))
                )

            }

            override fun onFailure(call: Call<S>, throwable: Throwable) =
                when (throwable) {
                    is IOException -> callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(ApiResponse.create(throwable, 0))
                    )
                    else -> {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(ApiResponse.create(throwable))
                        )
                    }
                }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun request(): Request = delegate.request()

    override fun execute(): Response<ApiResponse<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun timeout(): Timeout = delegate.timeout()
}
