package com.anangkur.baseproject.data

import android.content.Context
import android.content.SharedPreferences
import com.anangkur.baseproject.data.local.LocalRepository
import com.anangkur.baseproject.data.local.room.AppDao
import com.anangkur.baseproject.data.remote.RemoteRepository

object Injection {
    fun provideRepository(preferences: SharedPreferences, dao: AppDao) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(preferences, dao)
    )
}