package com.anangkur.uangkerja.feature.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.uangkerja.data.Repository
import com.anangkur.uangkerja.data.model.BasePagination
import com.anangkur.uangkerja.data.model.BaseResponse
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.data.model.transaction.Transaction

class HistoryTransaksiViewModel(private val repository: Repository): ViewModel(){

    private val reloadTrigger = MutableLiveData<Int>()
    private val tempListHistoryTransaction = ArrayList<Transaction>()
    val listHistoryTransactionLiveData = MutableLiveData<List<Transaction>>()
    val loadMoreLive = MutableLiveData<Boolean>()
    var positionStart = 0
    var differentCount = 0
    var nextPage = 0
    var pagelast = 0
    val resultListHistoryTransactionLiveData: LiveData<Result<BaseResponse<BasePagination<Transaction>>>> =
        Transformations.switchMap(reloadTrigger){
            repository.getHistoryTransaction(it)
        }
    fun getHistoryTransaction(page: Int?){
        reloadTrigger.value = page
    }
    fun paginateData(data: BasePagination<Transaction>){
        val latestData = ArrayList<Transaction>()
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