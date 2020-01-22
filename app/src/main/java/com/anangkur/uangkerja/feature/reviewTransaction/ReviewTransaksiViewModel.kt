package com.anangkur.uangkerja.feature.reviewTransaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.uangkerja.data.Repository
import com.anangkur.uangkerja.data.model.BaseResponse
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.data.model.transaction.Bank

class ReviewTransaksiViewModel(private val repository: Repository): ViewModel(){

    private val reloadTrigger = MutableLiveData<Boolean>()
    val listBankLiveData: LiveData<Result<BaseResponse<List<Bank>>>> =
        Transformations.switchMap(reloadTrigger){
            repository.getBank()
        }
    fun getListBank(){
        reloadTrigger.value = true
    }

    var selectedBank: Bank? = null
    val listBankStringLive = MutableLiveData<ArrayList<String>>()
    private val listBankTemp = ArrayList<Bank>()
    fun createListString(listBank: List<Bank>){
        listBankTemp.clear()
        listBankTemp.addAll(listBank)
        val listBankString = ArrayList<String>()
        for (bank in listBank){
            listBankString.add(bank.bankName)
        }
        listBankStringLive.value = listBankString
    }
    fun getBankSelected(position: Int): Bank? {
        return listBankTemp[position]
    }

}