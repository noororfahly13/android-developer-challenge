package com.example.network.vo

enum class Status {
    SUCCESS,
    ERROR,
    LOADING;

    fun isSuccess() = this == SUCCESS
    fun isError() = this == ERROR
    fun isLoading() = this == LOADING
}
