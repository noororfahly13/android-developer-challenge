package com.example.network.setup


import android.util.Log
import com.example.network.constants.ConnectionFlag.NO_AUTH_HEADER
import com.example.network.services.AuthTokenService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    private val authTokenService = AuthTokenService()

    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()

        val url: HttpUrl = originalRequest.url.newBuilder().build()

        originalRequest = originalRequest.newBuilder().url(url).build()

        if (originalRequest.headers(NO_AUTH_HEADER).isNotEmpty()) {
            return chain.proceed(
                originalRequest.newBuilder().removeHeader(NO_AUTH_HEADER).build()
            )
        }

        val requestBuilder = originalRequest.newBuilder()

        //Add auth token
        authTokenService.authToken.let {
            if (it.isNotBlank()) requestBuilder.addHeader("Authorization", it)
        }

        val request = requestBuilder.build()
        Log.d("request", "send request ${request.url}")
        return chain.proceed(request)
    }
}
