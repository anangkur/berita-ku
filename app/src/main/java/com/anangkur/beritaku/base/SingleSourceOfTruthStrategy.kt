package com.anangkur.beritaku.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.anangkur.beritaku.data.model.BaseResult
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> BaseResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<BaseResult<T>> =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading(true))
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == BaseResult.Status.SUCCESS) {
            emit(BaseResult.loading(false))
            saveCallResult(responseStatus.data!!)
            val source = databaseQuery.invoke().map { BaseResult.success(it) }
            emitSource(source)
        } else if (responseStatus.status == BaseResult.Status.ERROR) {
            emit(BaseResult.loading(false))
            emit(BaseResult.error(responseStatus.message!!))
            val source = databaseQuery.invoke().map { BaseResult.success(it) }
            emitSource(source)
        }
    }