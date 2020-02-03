package com.anangkur.sampingantest.data.remote

import com.anangkur.sampingantest.base.BaseDataSource
import com.anangkur.sampingantest.data.DataSource

class RemoteRepository: DataSource, BaseDataSource() {

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}