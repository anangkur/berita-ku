package com.anangkur.beritaku.data.remote

import com.anangkur.beritaku.base.BaseDataSource
import com.anangkur.beritaku.data.DataSource

class RemoteRepository: DataSource, BaseDataSource() {

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}