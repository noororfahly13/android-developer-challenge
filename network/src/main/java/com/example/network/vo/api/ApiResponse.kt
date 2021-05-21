package com.example.network.vo.api

import retrofit2.Response


@Suppress("unused")
// T is used in extending classes
sealed class ApiResponse<T> {

    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                // Empty body
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(
                    errorMessage ?: "Unknown error"
                )
            }
        }

        fun <T> create(error: Throwable, code: Int = -1): ApiErrorResponse<T> {

            val message = error.message ?: "Unknown error"

            return ApiErrorResponse(message, code)
        }
    }

    abstract fun isSuccessful(): Boolean

}

/**
 * separate class for HTTP 204 "No content" responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>() {
    override fun isSuccessful() = true
}

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>() {
    override fun isSuccessful() = true
}

data class ApiErrorResponse<T>(
    val errorMessage: String,
    val code: Int = 0,
    val rawResponse: String? = null
) : ApiResponse<T>() {
    override fun isSuccessful() = false
}
