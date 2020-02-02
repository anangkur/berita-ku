package com.anangkur.baseproject.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.anangkur.baseproject.data.DataSource
import com.anangkur.baseproject.data.local.room.AppDao

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