package com.anangkur.uangkerja.feature.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.uangkerja.data.Repository
import com.anangkur.uangkerja.data.model.BasePagination
import com.anangkur.uangkerja.data.model.BaseResponse
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.data.model.transaction.TransactionApi

class HistoryTransaksiViewModel(private val repository: Repository): ViewModel(){

    private val reloadTrigger = MutableLiveData<Int>()
    private val tempListHistoryTransaction = ArrayList<TransactionApi>()
    val listHistoryTransactionLiveData = MutableLiveData<List<TransactionApi>>()
    val loadMoreLive = MutableLiveData<Boolean>()
    var positionStart = 0
    var differentCount = 0
    var nextPage = 0
    var pagelast = 0
    val resultListHistoryTransactionLiveData: LiveData<Result<BaseResponse<BasePagination<TransactionApi>>>> =
        Transformations.switchMap(reloadTrigger){
            repository.getHistoryTransaction(it)
        }
    fun getHistoryTransaction(page: Int?){
        reloadTrigger.value = page
    }
    fun paginateData(data: BasePagination<TransactionApi>){
        val latestData = ArrayList<TransactionApi>()
        with(tempListHistoryTransaction){
            if (data.currentPage == 1){
                clear()
                nextPage = 1
                positionStart = 0
            } else {
                nextPage++
                positionStart = tempListHistoryTransaction.size
            }
            addAll(data.data)
            latestData.clear()
            latestData.addAll(data.data)
        }
        pagelast = data.currentPage
        loadMoreLive.value = pagelast < data.lastPage
        differentCount = tempListHistoryTransaction.size - positionStart
        listHistoryTransactionLiveData.value = latestData
    }

}