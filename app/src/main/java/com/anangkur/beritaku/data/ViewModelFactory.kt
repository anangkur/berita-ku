package com.anangkur.beritaku.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.beritaku.data.local.room.AppDatabase
import com.anangkur.beritaku.util.Const

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
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