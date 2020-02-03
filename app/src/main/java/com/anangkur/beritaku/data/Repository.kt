package com.anangkur.beritaku.data

import com.anangkur.beritaku.base.resultLiveData
import com.anangkur.beritaku.data.local.LocalRepository
import com.anangkur.beritaku.data.remote.RemoteRepository
import com.anangkur.beritaku.util.Const

class Repository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) {

    fun getTopHeadlinesNews() = resultLiveData(
        databaseQuery = { localRepository.getAllDataByCategory(Const.CATEGORY_GENERAL) },
        networkCall = { remoteRepository.getTopHeadlinesNews(
            Const.API_KEY,
            Const.COUNTRY_ID,
            Const.CATEGORY_GENERAL,
            null,
            null)
        },
        saveCallResult = {
            localRepository.deleteByCategory(Const.CATEGORY_GENERAL)
            localRepository.insertData(it.articles.map {article ->
                article.copy(category = Const.CATEGORY_GENERAL)
            })
        }
    )

    fun getBusinessNews() = resultLiveData(
        databaseQuery = { localRepository.getAllDataByCategory(Const.CATEGORY_BUSSINESS) },
        networkCall = { remoteRepository.getTopHeadlinesNews(
            Const.API_KEY,
            Const.COUNTRY_ID,
            Const.CATEGORY_BUSSINESS,
            null,
            null)
        },
        saveCallResult = {
            localRepository.deleteByCategory(Const.CATEGORY_BUSSINESS)
            localRepository.insertData(it.articles.map {article ->
                article.copy(category = Const.CATEGORY_BUSSINESS)
            })
        }
    )

    fun getTechNews() = resultLiveData(
        databaseQuery = { localRepository.getAllDataByCategory(Const.CATEGORY_TECHNOLOGY) },
        networkCall = { remoteRepository.getTopHeadlinesNews(
            Const.API_KEY,
            Const.COUNTRY_ID,
            Const.CATEGORY_TECHNOLOGY,
            null,
            null)
        },
        saveCallResult = {
            localRepository.deleteByCategory(Const.CATEGORY_TECHNOLOGY)
            localRepository.insertData(it.articles.map {article ->
                article.copy(category = Const.CATEGORY_TECHNOLOGY)
            })
        }
    )

    fun getSportNews() = resultLiveData(
        databaseQuery = { localRepository.getAllDataByCategory(Const.CATEGOGY_SPORT) },
        networkCall = { remoteRepository.getTopHeadlinesNews(
            Const.API_KEY,
            Const.COUNTRY_ID,
            Const.CATEGOGY_SPORT,
            null,
            null)
        },
        saveCallResult = {
            localRepository.deleteByCategory(Const.CATEGOGY_SPORT)
            localRepository.insertData(it.articles.map {article ->
                article.copy(category = Const.CATEGOGY_SPORT)
            })
        }
    )

    companion object{
        @Volatile private var INSTANCE: Repository? = null
        fun getInstance(remoteRepository: RemoteRepository, localRepository: LocalRepository) = INSTANCE ?: synchronized(
            Repository::class.java){
            INSTANCE ?: Repository(remoteRepository, localRepository).also { INSTANCE = it }
        }
    }
}