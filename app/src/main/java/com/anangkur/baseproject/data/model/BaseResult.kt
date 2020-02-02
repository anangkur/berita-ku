package com.anangkur.baseproject.data.model

data class BaseResult<out T>(val status: Status, val data: T?, val message: String?, val isLoading: Boolean?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): BaseResult<T> {
            return BaseResult(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String): BaseResult<T> {
            return BaseResult(Status.ERROR, null, message, null)
        }

        fun <T> loading(isLoading: Boolean): BaseResult<T> {
            return BaseResult(Status.LOADING, null, null, isLoading)
        }
    }
}