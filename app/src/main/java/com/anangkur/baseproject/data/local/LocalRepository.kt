package com.anangkur.baseproject.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.anangkur.baseproject.data.DataSource

class LocalRepository(private val context: Context, private val preferences: SharedPreferences): DataSource {

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: LocalRepository? = null
        fun getInstance(context: Context, preferences: SharedPreferences) = INSTANCE ?: LocalRepository(context, preferences)
    }
}