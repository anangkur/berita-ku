package com.anangkur.beritaku.data.remote

import com.anangkur.beritaku.base.BaseDataSource
import com.anangkur.beritaku.data.DataSource
import com.anangkur.beritaku.data.model.BaseResult
import com.anangkur.beritaku.data.model.news.GetNewsResponse

class RemoteRepository: DataSource, BaseDataSource() {

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<GetNewsResponse> {
        return getResult {
            ApiService.getApiService.getTopHeadlinesNews(
                apiKey,
                country,
                category,
                sources,
                q
            )
        }
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}