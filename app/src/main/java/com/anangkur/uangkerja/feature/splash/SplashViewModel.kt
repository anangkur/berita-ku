package com.anangkur.uangkerja.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.uangkerja.data.Repository
import com.anangkur.uangkerja.data.model.BaseResponse
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.data.model.config.ConfigCoin

class SplashViewModel(private val repository: Repository): ViewModel(){

    fun isLoggedIn() = !repository.loadApiToken().isNullOrEmpty()

    private val reloadTrigger = MutableLiveData<Boolean>()

    fun getConfigCoin(){
        reloadTrigger.value = true
    }

    val resultConfigCoinLiveData: LiveData<Result<BaseResponse<List<ConfigCoin>>>> =
        Transformations.switchMap(reloadTrigger){
            repository.getConfigCoin()
        }

    fun saveConfigCoin(currency: Int){
        repository.saveConfigCoin(currency)
    }
}