package com.anangkur.sampingantest.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.anangkur.sampingantest.data.DataSource
import com.anangkur.sampingantest.data.local.room.AppDao

class LocalRepository(
    private val preferences: SharedPreferences,
    private val dao: AppDao
): DataSource {

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: LocalRepository? = null
        fun getInstance(
            preferences: SharedPreferences,
            dao: AppDao
        ) = INSTANCE ?: LocalRepository(preferences, dao)
    }
}