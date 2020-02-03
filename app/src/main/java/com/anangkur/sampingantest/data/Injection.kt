package com.anangkur.sampingantest.data

import android.content.SharedPreferences
import com.anangkur.sampingantest.data.local.LocalRepository
import com.anangkur.sampingantest.data.local.room.AppDao
import com.anangkur.sampingantest.data.remote.RemoteRepository

object Injection {
    fun provideRepository(preferences: SharedPreferences, dao: AppDao) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(preferences, dao)
    )
}