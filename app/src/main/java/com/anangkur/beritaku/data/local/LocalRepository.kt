package com.anangkur.beritaku.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.anangkur.beritaku.data.DataSource
import com.anangkur.beritaku.data.local.room.AppDao
import com.anangkur.beritaku.data.model.news.Article

class LocalRepository(
    private val preferences: SharedPreferences,
    private val dao: AppDao
): DataSource {

    override suspend fun insertData(data: List<Article>) { dao.insertData(data) }

    override suspend fun deleteByCategory(category: String) { dao.deleteByCategory(category) }

    override fun getAllDataByCategory(category: String): LiveData<List<Article>> {
        return dao.getAllDataByCategory(category)
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: LocalRepository? = null
        fun getInstance(
            preferences: SharedPreferences,
            dao: AppDao
        ) = INSTANCE ?: LocalRepository(preferences, dao)
    }
}