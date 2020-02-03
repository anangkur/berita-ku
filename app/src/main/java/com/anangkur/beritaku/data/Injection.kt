package com.anangkur.beritaku.data

import android.content.SharedPreferences
import com.anangkur.beritaku.data.local.LocalRepository
import com.anangkur.beritaku.data.local.room.AppDao
import com.anangkur.beritaku.data.remote.RemoteRepository

object Injection {
    fun provideRepository(preferences: SharedPreferences, dao: AppDao) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(preferences, dao)
    )
}