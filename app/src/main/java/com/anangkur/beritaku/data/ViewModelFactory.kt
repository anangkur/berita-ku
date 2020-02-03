package com.anangkur.beritaku.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.beritaku.data.local.room.AppDatabase
import com.anangkur.beritaku.feature.detail.DetailViewModel
import com.anangkur.beritaku.feature.home.HomeViewModel
import com.anangkur.beritaku.feature.splash.SplashViewModel
import com.anangkur.beritaku.util.Const

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(repository)
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java){
                INSTANCE ?: ViewModelFactory(
                    Injection.provideRepository(
                        context.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE),
                        AppDatabase.getDatabase(context).getDao()
                    )).also { INSTANCE = it }
            }
    }
}