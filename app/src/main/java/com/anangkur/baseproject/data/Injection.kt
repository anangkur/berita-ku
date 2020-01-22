package com.anangkur.baseproject.data

import android.content.Context
import android.content.SharedPreferences
import com.anangkur.baseproject.data.local.LocalRepository
import com.anangkur.baseproject.data.remote.RemoteRepository

object Injection {
    fun provideRepository(context: Context, preferences: SharedPreferences) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(context, preferences)
    )
}