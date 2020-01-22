package com.anangkur.baseproject.data.remote

import com.anangkur.baseproject.base.BaseDataSource
import com.anangkur.baseproject.data.DataSource

class RemoteRepository: DataSource, BaseDataSource() {

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}