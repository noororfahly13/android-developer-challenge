package com.example.network.setup


import com.example.network.constants.ConnectionFlag.NO_AUTH_HEADER
import com.example.network.services.AuthTokenService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    private val authTokenService = AuthTokenService()

    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()

        val url: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", "6ddf1da8ede343f82786973e2dd7c457").build()
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
        return chain.proceed(request)
    }
}
